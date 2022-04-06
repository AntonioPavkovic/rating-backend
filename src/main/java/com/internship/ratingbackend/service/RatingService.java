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


@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final Integer ALLOWED_RANGE_DATE_DAYS = 30;
    private final AppProperties appProperties;
    private final Slack slack;


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

    @Scheduled(cron = "0 59 23 * * *")
    @SneakyThrows
    public void sendSlackReport() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime morningDateTime = LocalDateTime.now();

        morningDateTime= morningDateTime.minusHours(localDateTime.getHour())
                .minusMinutes(localDateTime.getMinute())
                .minusSeconds(localDateTime.getSecond());

        List<Rating> list = ratingRepository.getRatingByCreatedAtBetween(morningDateTime, localDateTime);

        if((long) list.size() < 50) {
            log.info("Sending scheduled slack report! Ratings are lower then 50");

            String webhookUrl = appProperties.getSlackReportLink();
            String payload =  "{\"text\":\"There has been less than 50 ratings today!\"}";

            WebhookResponse response = slack.send(webhookUrl, payload);

            log.info(response.toString());

        }


    }
}