package com.zeyilinxin.heallinlib.chat;

public enum ServerVersion
{
    v1_8_R1(100801),
    v1_8_R2(100802),
    v1_8_R3(100803),
    v1_9_R1(100901),
    v1_9_R2(100902),
    v1_10_R1(101001),
    v1_11_R1(101101),
    v1_12_R1(101201);

    private int version;

    private ServerVersion(final int version) {
        this.version = version;
    }

    public boolean after(final ServerVersion server) {
        return this.version >= server.version;
    }

    public boolean between(final ServerVersion min, final ServerVersion max) {
        return this.version >= min.version && this.version <= max.version;
    }
}
