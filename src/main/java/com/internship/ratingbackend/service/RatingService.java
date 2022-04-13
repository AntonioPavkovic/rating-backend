package com.internship.ratingbackend.service;

import com.internship.ratingbackend.config.AppProperties;
import com.internship.ratingbackend.dto.rating.RatingResponse;
import com.internship.ratingbackend.model.Rating;
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

/**
 * Service class for Rating
 *
 * @see Rating
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final Integer ALLOWED_RANGE_DATE_DAYS = 30;
    private final AppProperties appProperties;
    private final Slack slack;


    /**
     * Method that requests two dates and finds ratings between those dates
     * @param fromDate
     * @param toDate
     * @return List of ratings
     */


    public List<Rating> getRatingByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {

        Duration duration = Duration.between(fromDate, toDate);

        if (fromDate.isAfter(toDate))
            throw new IllegalArgumentException("'from date' must be before 'to date'");

        if (duration.toDays() > ALLOWED_RANGE_DATE_DAYS)
            throw new IllegalArgumentException("Date range must be 30 days or less");

        return ratingRepository.getRatingByCreatedAtBetween(fromDate, toDate);

    }

    /**
     * Method that creates a new rating
     *
     * @param rating
     */

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    /**
     * Method that converts ratings between two dates into a list of rating responses
     *
     * @param fromDate
     * @param toDate
     * @return list of rating responses
     */

    public List<RatingResponse> buildAll(LocalDateTime fromDate, LocalDateTime toDate)
    {

        List<RatingResponse> ratingResponses =new ArrayList<>();

        for (Rating rating:this.getRatingByCreatedAtBetween(fromDate,toDate))
        {
            ratingResponses.add(buildSingle(rating));
        }

        return ratingResponses;
    }

    /**
     * Method that converts single rating into a rating response
     *
     * @param rating
     * @return rating response
     */

    public RatingResponse buildSingle(Rating rating)
    {
        return new RatingResponse(rating);
    }


    /**
     * Method that is scheduled to daily check ratings, and, if the ratings are below a certain percentage
     * it will send a message to slack
     *
     */


    @Scheduled(fixedRate = 60000)
    @SneakyThrows
    public void sendSlackReport() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime morningDateTime = LocalDateTime.now();

        morningDateTime= morningDateTime.minusHours(localDateTime.getHour())
                .minusMinutes(localDateTime.getMinute())
                .minusSeconds(localDateTime.getSecond());

        List<Rating> list = ratingRepository.getRatingByCreatedAtBetween(morningDateTime, localDateTime);
        int lowRatingCounter=0;
        for (Rating rating:list) {
            if(rating.getEmotion().getId()==1)
                lowRatingCounter++;
        }

        int percentage = (70 * list.size()) / 100;

        if(lowRatingCounter>percentage) {
            log.info("Sending scheduled slack report! Today's ratings are low!");

            String webhookUrl = appProperties.getSlackReportLink();
            String payload =  "{\"text\":\"Today's ratings are low!!\"}";

            WebhookResponse response = slack.send(webhookUrl, payload);

            log.info(response.toString());

        }
    }
}