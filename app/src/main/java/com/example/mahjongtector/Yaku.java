package com.example.mahjongtector;

import androidx.annotation.NonNull;

public enum Yaku {
    Reach(0), Ippatu(1), Tumo(2), Pinhu(3), Tanyao(4), Ipeiko(5), Haku(6), Hatu(7), Tyun(8), Jikaze(9), Bakaze(10), Rinsyan(11), Tyankan(12), Haitei(13), Houtei(14), DoubleReach(15), Tyanta(16), Honroutou(17), SansyokuDoujun(18), Ittuu(19), ToiToi(20), SansyokuDoukou(21), Sanankou(22), Sankantu(23), Syousangen(24), Titoitu(25), Ryanpeikou(26), Juntyan(27), Honitu(28), Tinitu(29), Dora(30), Suankou(31), SuankouTanki(32), Daisangen(33), Tuisou(34), Syoususi(35), Daisusi(36), Ryuisou(37), Tyurenpoutou(38), JunseiTyurenpoutou(39), Tinroutou(40), Sukantu(41), Tenhou(42), Tihou(43), Kokusimusou(44), Kokusimusou13(45);

    private int code;
    Yaku(int code) {
        this.code = code;
    }

    public static Yaku valueOf(int c) {
        return Yaku.values()[c];
    }

    public int getCode() {
        return code;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
