package com.example.mahjongtector;

import java.util.List;

public class IllegalMentsuSizeException extends Mahjong4jException {
    private List<Mentsu> mentsuList;

    public IllegalMentsuSizeException(List<Mentsu> mentsuList) {
        super("面子の組が和了の形になっていません");
        this.mentsuList = mentsuList;
    }

    public String getAdvice() {
        return "面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが" + mentsuList.size() + "個の面子が見つかりました";
    }

    /**
     * @return 誤っていると判定されている面子の組を返します
     */
    public List<Mentsu> getMentsuList() {
        return mentsuList;
    }
}