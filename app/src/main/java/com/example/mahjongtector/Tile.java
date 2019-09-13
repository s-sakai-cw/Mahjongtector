package com.example.mahjongtector;

public enum Tile {
    M1(0, TileType.MANZU, 1),
    M2(1, TileType.MANZU, 2),
    M3(2, TileType.MANZU, 3),
    M4(3, TileType.MANZU, 4),
    M5(4, TileType.MANZU, 5),
    M6(5, TileType.MANZU, 6),
    M7(6, TileType.MANZU, 7),
    M8(7, TileType.MANZU, 8),
    M9(8, TileType.MANZU, 9),

    P1(9, TileType.PINZU, 1),
    P2(10, TileType.PINZU, 2),
    P3(11, TileType.PINZU, 3),
    P4(12, TileType.PINZU, 4),
    P5(13, TileType.PINZU, 5),
    P6(14, TileType.PINZU, 6),
    P7(15, TileType.PINZU, 7),
    P8(16, TileType.PINZU, 8),
    P9(17, TileType.PINZU, 9),

    S1(18, TileType.SOHZU, 1),
    S2(19, TileType.SOHZU, 2),
    S3(20, TileType.SOHZU, 3),
    S4(21, TileType.SOHZU, 4),
    S5(22, TileType.SOHZU, 5),
    S6(23, TileType.SOHZU, 6),
    S7(24, TileType.SOHZU, 7),
    S8(25, TileType.SOHZU, 8),
    S9(26, TileType.SOHZU, 9),

    TON(27, TileType.FONPAI, 0),//東
    NAN(28, TileType.FONPAI, 0),//南
    SHA(29, TileType.FONPAI, 0),//西
    PEI(30, TileType.FONPAI, 0),//北

    HAK(31, TileType.SANGEN, 0),//白
    HAT(32, TileType.SANGEN, 0),//発
    CHN(33, TileType.SANGEN, 0);//中

    private int code;
    private TileType type;
    private int number;

    Tile(int code, TileType type, int number) {
        this.code = code;
        this.type = type;
        this.number = number;
    }

    public static Tile valueOf(int c) {
        return Tile.values()[c];
    }

    public int getCode() {
        return code;
    }

    public TileType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public boolean isYaochu() {
        return number == 0 || number == 1 || number == 9;
    }
}
