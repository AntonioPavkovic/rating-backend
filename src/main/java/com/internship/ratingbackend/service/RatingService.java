package com.internship.ratingbackend.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.internship.ratingbackend.config.AppProperties;
import com.internship.ratingbackend.dto.rating.RatingResponse;

import com.internship.ratingbackend.model.Rating;

import com.internship.ratingbackend.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final Integer ALLOWED_RANGE_DATE_DAYS = 30;
    private final AppProperties appProperties;
    private final Slack slack;
//    private final EmotionRepository emotionRepository;



//    @EventListener(ApplicationReadyEvent.class)
//    public void fillUpDB() {
//        for(int i= 1; i<50; i++) {
//            double randomDouble = ((Math.random() * (5 - 1)) + 1);
//            Long random = Math.round(randomDouble);
//            long endDate = Instant.now().getEpochSecond();
//            long startDate = 1625913220L;
//            long randomEpoch = ThreadLocalRandom
//                    .current()
//                    .nextLong(startDate, endDate);
//            Instant randomDate = Instant.ofEpochSecond(randomEpoch);
//            Optional<Emotion> emoji = emotionRepository.findById(Math.toIntExact(random));
//            if(emoji.isPresent()) {
//                Rating rating = new Rating(emoji.get(), randomDate);
//                ratingRepository.save(rating);
//            }
//        }
//    }

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

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
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