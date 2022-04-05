package com.internship.ratingbackend.service;

import com.internship.ratingbackend.config.AppProperties;
import com.internship.ratingbackend.dto.rating.RatingRequest;
import com.internship.ratingbackend.dto.rating.RatingResponse;

import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.model.Rating;

import com.internship.ratingbackend.repository.EmotionRepository;
import com.internship.ratingbackend.repository.RatingRepository;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final EmotionRepository emotionRepository;
    private final Integer ALLOWED_RANGE_DATE_DAYS = 30;
    private final AppProperties appProperties;
    private final Slack slack;



    /*
     * Method takes two parameters, 'fromDate' and 'toDate' and returns ratings between those two dates
     * @param fromDate
     * @param toDate
     * */

    public List<Rating> getRatingByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {

        Duration duration = Duration.between(fromDate, toDate);

        if (fromDate.isAfter(toDate))
            throw new IllegalArgumentException("'from date' must be before 'to date'");

        if (duration.toDays() > ALLOWED_RANGE_DATE_DAYS)
            throw new IllegalArgumentException("Date range must be 30 days or less");

        return ratingRepository.getRatingByCreatedAtBetween(fromDate, toDate);

    }

//    public void createRating(Rating rating) {
//        ratingRepository.save(rating);
//    }

    public RatingResponse createRating(RatingRequest request) {

        RatingResponse response = new RatingResponse();
        Optional<Emotion> emotion = emotionRepository.findById(request.getEmotionId());

        if(emotion.isPresent()) {

            Rating rating = new Rating(emotion.get());
            ratingRepository.save(rating);

            response.setRating(rating);

            return response;
        }

        log.info("400 Bad Request!");
        return response;

    }

    public List<RatingResponse> buildAll(LocalDateTime fromDate, LocalDateTime toDate)
    {

        List<RatingResponse> ratingResponses =new ArrayList<>();

        for (Rating rating:this.getRatingByCreatedAtBetween(fromDate,toDate))
        {
            ratingResponses.add(buildSingle(rating));
        }

        return ratingResponses;
    }

    public RatingResponse buildSingle(Rating rating)
    {
        return new RatingResponse(rating);
    }

    @Scheduled(cron = "*/86400 * * * * *")
    @SneakyThrows
    public void sendSlackReport() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime morningDateTime = LocalDateTime.now();

        morningDateTime= morningDateTime.minusHours(localDateTime.getHour())
                .minusMinutes(localDateTime.getMinute())
                .minusSeconds(localDateTime.getSecond());

        List<Rating> list = ratingRepository.getRatingByCreatedAtBetween(morningDateTime, localDateTime);


        if((long) list.size() < 10) {
            log.info("Sending scheduled slack report! Ratings are lower then 10");

            String webhookUrl = appProperties.getSlackReportLink();
            String payload =  "{\"text\":\"There has been less than 10 ratings today!\"}";

            WebhookResponse response = slack.send(webhookUrl, payload);

            log.info(response.toString());

        }


    }
}