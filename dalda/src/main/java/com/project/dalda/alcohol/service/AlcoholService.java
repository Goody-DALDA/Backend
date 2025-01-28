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
public class AlcoholService {

    private final SojuRepository sojuRepository ;
    private final BeerRepository beerRepository;
    private final SakeRepository sakeRepository;
    private final TraditionalLiquorRepository traditionalLiquorRepository;
    private final WineRepository wineRepository;
    private final WhiskyRepository whiskyRepository;

    public List<AlcoholResponseDto> getAllAlcohols() {
        List<AlcoholResponseDto> allAlcohols = new ArrayList<>();
        allAlcohols.addAll(getSakeAlcohols());
        allAlcohols.addAll(getTraditionalLiquorAlcohols());
        allAlcohols.addAll(getSojuAlcohols());
        allAlcohols.addAll(getWhiskyAlcohols());
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

    public List<AlcoholResponseDto> getWhiskyAlcohols() {
        return whiskyRepository.findAll()
                .stream()
                .map(WhiskyResponseDto::of)
                .collect(Collectors.toList());
    }

}
