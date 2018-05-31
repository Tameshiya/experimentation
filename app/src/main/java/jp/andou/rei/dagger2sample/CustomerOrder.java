package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rei on 18/06/01.
 */

public class CustomerOrder {


    @SerializedName("orderId")
    private int orderId;

    @SerializedName("assembly")
    private int assembly;

    @SerializedName("assemblyCount")
    private int assemblyCounter;

    @SerializedName("delivery")
    private boolean delivery;

    @SerializedName("goodsNameCount")
    private int goodsNameCounter;

    @SerializedName("totalCount")
    private int totalCounter;

    @SerializedName("queueTime")
    private String expiredTime;

    @Nullable
    @SerializedName("performerName")
    private String performerName;

    public int getOrderId() {
        return orderId;
    }

    public int getAssembly() {
        return assembly;
    }

    public int getAssemblyCounter() {
        return assemblyCounter;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public int getGoodsNameCounter() {
        return goodsNameCounter;
    }

    public int getTotalCounter() {
        return totalCounter;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    @Nullable
    public String getPerformerName() {
        return performerName;
    }

}
