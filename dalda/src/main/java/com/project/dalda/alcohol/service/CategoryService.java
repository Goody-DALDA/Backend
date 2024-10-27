package com.project.dalda.alcohol.service;

import com.project.dalda.alcohol.repository.*;
import com.project.dalda.alcohol.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SojuRepository sojuRepository ;
    private final BeerRepository beerRepository;
    private final SakeRepository sakeRepository;
    private final TraditionalLiquorRepository traditionalLiquorRepository;
    private final WineRepository wineRepository;
    private final WiskyRepository wiskyRepository;

    public List<AlcoholResponseDto> getAllAlcohols() {
        List<AlcoholResponseDto> allAlcohols = new ArrayList<>();
        allAlcohols.addAll(getSakeAlcohols());
        allAlcohols.addAll(getTraditionalLiquorAlcohols());
        allAlcohols.addAll(getSojuAlcohols());
        allAlcohols.addAll(getWiskyAlcohols());
        allAlcohols.addAll(getBeerAlcohols());
        allAlcohols.addAll(getWineAlcohols());
        return allAlcohols;
    }

    public List<AlcoholResponseDto> getSojuAlcohols() {
        return sojuRepository.findAll()
                .stream()
                .map(SojuResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AlcoholResponseDto> getBeerAlcohols() {
        return beerRepository.findAll()
                .stream()
                .map(BeerResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AlcoholResponseDto> getSakeAlcohols() {
        return sakeRepository.findAll()
                .stream()
                .map(SakeResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AlcoholResponseDto> getTraditionalLiquorAlcohols() {
        return traditionalLiquorRepository.findAll()
                .stream()
                .map(TraditionalLiquorResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AlcoholResponseDto> getWineAlcohols() {
        return wineRepository.findAll()
                .stream()
                .map(WineResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AlcoholResponseDto> getWiskyAlcohols() {
        return wiskyRepository.findAll()
                .stream()
                .map(WiskyResponseDto::of)
                .collect(Collectors.toList());
    }

}
