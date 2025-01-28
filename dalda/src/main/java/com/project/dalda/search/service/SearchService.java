package com.project.dalda.search.service;

import com.project.dalda.alcohol.repository.*;
import com.project.dalda.alcohol.response.*;
import com.project.dalda.search.entity.Search;
import com.project.dalda.search.repository.*;
import com.project.dalda.search.response.SearchResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final SojuRepository sojuRepository ;
    private final BeerRepository beerRepository;
    private final SakeRepository sakeRepository;
    private final TraditionalLiquorRepository traditionalLiquorRepository;
    private final WineRepository wineRepository;
    private final WhiskyRepository whiskyRepository;


    public List<SearchResponseDto> getSearchBasedRecommendations(String recommendations, int maxResults) {
        return searchRepository.findRecommendations(recommendations).stream()
                .limit(maxResults)
                .map(SearchResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void incrementSearchCount(Long id) {
        Search search = searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
        if (search != null) {
            search.incrementSearchCount();
            searchRepository.save(search);
        }
    }

    public AlcoholResponseDto getSojuAlcohols(Long id) {
        return sojuRepository.findById(id)
                .map(SojuResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("Soju not found with id: " + id));
    }

    public AlcoholResponseDto getBeerAlcohols(Long id) {
        return beerRepository.findById(id)
                .map(BeerResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("Beer not found with id: " + id));
    }

    public AlcoholResponseDto getSakeAlcohols(Long id) {
        return sakeRepository.findById(id)
                .map(SakeResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("Sake not found with id: " + id));
    }

    public AlcoholResponseDto getTraditionalLiquorAlcohols(Long id) {
        return traditionalLiquorRepository.findById(id)
                .map(TraditionalLiquorResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("TraditionalLiquor not found with id: " + id));
    }

    public AlcoholResponseDto getWineAlcohols(Long id) {
        return wineRepository.findById(id)
                .map(WineResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("Wine not found with id: " + id));
    }

    public AlcoholResponseDto getWhiskyAlcohols(Long id) {
        return whiskyRepository.findById(id)
                .map(WhiskyResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("Whisky not found with id: " + id));
    }

    public Map<String, List<AlcoholResponseDto>> getSearchData(String name) {
        Map<String, List<AlcoholResponseDto>> data = new HashMap<>();
        List<AlcoholResponseDto> beerData = new ArrayList<>();
        List<AlcoholResponseDto> sakeData = new ArrayList<>();
        List<AlcoholResponseDto> sojuData = new ArrayList<>();
        List<AlcoholResponseDto> wineData = new ArrayList<>();
        List<AlcoholResponseDto> traditionalLiquorData = new ArrayList<>();
        List<AlcoholResponseDto> whiskyData = new ArrayList<>();

        List<Search> searchData = searchRepository.getSearchData(name);
        for(Search search : searchData) {
            Long alcoholId = search.getAlcoholId();
            switch (search.getCategory().toLowerCase()) {
                case "soju":
                    sojuData.add(getSojuAlcohols(alcoholId));
                    break;
                case "beer":
                    beerData.add(getBeerAlcohols(alcoholId));
                    break;
                case "wine":
                    wineData.add(getWineAlcohols(alcoholId));
                    break;
                case "sake":
                    sakeData.add(getSakeAlcohols(alcoholId));
                    break;
                case "traditional_liquor":
                    traditionalLiquorData.add(getTraditionalLiquorAlcohols(alcoholId));
                    break;
                case "whisky":
                    whiskyData.add(getWhiskyAlcohols(alcoholId));
                    break;
                default:
                    throw new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.");
            }
        }
        data.put("beer", beerData);
        data.put("sake", sakeData);
        data.put("soju", sojuData);
        data.put("wine", wineData);
        data.put("traditionalLiquor", traditionalLiquorData);
        data.put("whisky", whiskyData);

        return data;
    }

    public void increaseViewCount(int alcoholId, String category) {

        Search search = searchRepository.findByAlcoholIdAndCategory(alcoholId, category);
        if(search == null) {
            throw new IllegalArgumentException("해당 알코올 정보를 찾을 수 없습니다.");
        }
        search.incrementSearchCount();
        searchRepository.save(search);
    }

}
