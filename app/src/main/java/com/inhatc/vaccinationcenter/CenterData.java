package com.inhatc.vaccinationcenter;

import com.fasterxml.jackson.annotation.*;

public class CenterData {
    private long currentCount;
    private Datum[] data;
    private long matchCount;
    private long page;
    private long perPage;
    private long totalCount;

    @JsonProperty("currentCount")
    public long getCurrentCount() { return currentCount; }
    @JsonProperty("currentCount")
    public void setCurrentCount(long value) { this.currentCount = value; }

    @JsonProperty("data")
    public Datum[] getData() { return data; }
    @JsonProperty("data")
    public void setData(Datum[] value) { this.data = value; }

    @JsonProperty("matchCount")
    public long getMatchCount() { return matchCount; }
    @JsonProperty("matchCount")
    public void setMatchCount(long value) { this.matchCount = value; }

    @JsonProperty("page")
    public long getPage() { return page; }
    @JsonProperty("page")
    public void setPage(long value) { this.page = value; }

    @JsonProperty("perPage")
    public long getPerPage() { return perPage; }
    @JsonProperty("perPage")
    public void setPerPage(long value) { this.perPage = value; }

    @JsonProperty("totalCount")
    public long getTotalCount() { return totalCount; }
    @JsonProperty("totalCount")
    public void setTotalCount(long value) { this.totalCount = value; }
}

