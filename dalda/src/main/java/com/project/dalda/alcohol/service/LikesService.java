package com.project.dalda.alcohol.service;

import com.project.dalda.alcohol.entity.*;
import com.project.dalda.alcohol.repository.*;
import com.project.dalda.alcohol.request.LikesDto;
import com.project.dalda.alcohol.response.LikesResponseDto;
import com.project.dalda.common.response.CommonResponse;
import com.project.dalda.users.entity.Users;
import com.project.dalda.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likeRepository ;
    private final UsersRepository userRepository ;
    private final SojuRepository sojuRepository ;
    private final BeerRepository beerRepository;
    private final SakeRepository sakeRepository;
    private final TraditionalLiquorRepository traditionalLiquorRepository;
    private final WineRepository wineRepository;
    private final WiskyRepository wiskyRepository;

    public String likeAlcohol(Long alcoholId, Long userId, String alcoholType, Long likeId) {
        String status = "active";

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        Optional<Likes> likes = likeRepository.findById(likeId);
        if(likes.isEmpty()) {
            Likes like = new LikesDto().toEntity(alcoholId, alcoholType, user, status);
            likeRepository.save(like);
        } else {
            String likeStatus = likes.get().getStatus();
            status = "active";
            if(likeStatus.equals("active")) {
                status = "inActive";
            }
            likes.get().update(status);
            likeRepository.save(likes.get());
        }
        return status;
    }

    public List<Object> getLikeList(Long userId) {
        List<Likes> likes = likeRepository.findByUserId_IdAndStatus(userId, "active");
        List<Long> sojuIds = new ArrayList<>();
        List<Long> beerIds = new ArrayList<>();
        List<Long> sakeIds = new ArrayList<>();
        List<Long> traditionalLiquorIds = new ArrayList<>();
        List<Long> wineIds = new ArrayList<>();
        List<Long> wiskyIds = new ArrayList<>();
        for (Likes like : likes) {
            if ("soju".equals(like.getAlcoholType())) {
                sojuIds.add(like.getAlcoholId());
            } else if ("beer".equals(like.getAlcoholType())) {
                beerIds.add(like.getAlcoholId());
            } else if ("wisky".equals(like.getAlcoholType())) {
                wiskyIds.add(like.getAlcoholId());
            } else if ("sake".equals(like.getAlcoholType())) {
                sakeIds.add(like.getAlcoholId());
            } else if ("traditionalLiquor".equals(like.getAlcoholType())) {
                traditionalLiquorIds.add(like.getAlcoholId());
            } else if ("wine".equals(like.getAlcoholType())) {
                wineIds.add(like.getAlcoholId());
            }
        }

        List<Soju> sojus = sojuRepository.findByIdIn(sojuIds);
        List<Beer> beers = beerRepository.findByIdIn(beerIds);
        List<TraditionalLiquor> traditionalLiquors = traditionalLiquorRepository.findByIdIn(traditionalLiquorIds);
        List<Sake> sakes = sakeRepository.findByIdIn(sakeIds);
        List<Wine> wines = wineRepository.findByIdIn(wineIds);
        List<Wisky> wiskies = wiskyRepository.findByIdIn(wiskyIds);

        List<Object> likedAlcohols = new ArrayList<>();
        likedAlcohols.addAll(sojus);
        likedAlcohols.addAll(beers);
        likedAlcohols.addAll(traditionalLiquors);
        likedAlcohols.addAll(sakes);
        likedAlcohols.addAll(wines);
        likedAlcohols.addAll(wiskies);

        return likedAlcohols;
    }
}
