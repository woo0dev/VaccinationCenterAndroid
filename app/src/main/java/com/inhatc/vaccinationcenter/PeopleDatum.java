package com.inhatc.vaccinationcenter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class PeopleDatum {
    private long accumulatedFirstCnt;
    private long accumulatedSecondCnt;
    private OffsetDateTime baseDate;
    private long firstCnt;
    private long secondCnt;
    private String sido;
    private long totalFirstCnt;
    private long totalSecondCnt;

    @JsonProperty("accumulatedFirstCnt")
    public long getAccumulatedFirstCnt() { return accumulatedFirstCnt; }
    @JsonProperty("accumulatedFirstCnt")
    public void setAccumulatedFirstCnt(long value) { this.accumulatedFirstCnt = value; }

    @JsonProperty("accumulatedSecondCnt")
    public long getAccumulatedSecondCnt() { return accumulatedSecondCnt; }
    @JsonProperty("accumulatedSecondCnt")
    public void setAccumulatedSecondCnt(long value) { this.accumulatedSecondCnt = value; }

    @JsonProperty("baseDate")
    public OffsetDateTime getBaseDate() { return baseDate; }
    @JsonProperty("baseDate")
    public void setBaseDate(OffsetDateTime value) { this.baseDate = value; }

    @JsonProperty("firstCnt")
    public long getFirstCnt() { return firstCnt; }
    @JsonProperty("firstCnt")
    public void setFirstCnt(long value) { this.firstCnt = value; }

    @JsonProperty("secondCnt")
    public long getSecondCnt() { return secondCnt; }
    @JsonProperty("secondCnt")
    public void setSecondCnt(long value) { this.secondCnt = value; }

    @JsonProperty("sido")
    public String getSido() { return sido; }
    @JsonProperty("sido")
    public void setSido(String value) { this.sido = value; }

    @JsonProperty("totalFirstCnt")
    public long getTotalFirstCnt() { return totalFirstCnt; }
    @JsonProperty("totalFirstCnt")
    public void setTotalFirstCnt(long value) { this.totalFirstCnt = value; }

    @JsonProperty("totalSecondCnt")
    public long getTotalSecondCnt() { return totalSecondCnt; }
    @JsonProperty("totalSecondCnt")
    public void setTotalSecondCnt(long value) { this.totalSecondCnt = value; }

    public PeopleDatum(String sido, long totalFirstCnt, long totalSecondCnt, long firstCnt, long secondCnt){
        this.sido = sido;
        this.totalFirstCnt = totalFirstCnt;
        this.totalSecondCnt = totalSecondCnt;
        this.firstCnt = firstCnt;
        this.secondCnt = secondCnt;
    }

}
