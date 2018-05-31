package jp.andou.rei.dagger2sample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerInfo {

    @SerializedName("customerName")
    private String customerName;

    @SerializedName("queueTime")
    private String expiredTime;

    @SerializedName("goodsNameCount")
    private int goodsNameCounter;

    @SerializedName("totalCount")
    private int totalCount;

    @SerializedName("assembly")
    private int assembly;

    @SerializedName("assemblyCount")
    private int assemblyCount;

    @SerializedName("orders")
    private List<CustomerOrder> customerOrders;


    public String getCustomerName() {
        return customerName;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public int getGoodsNameCounter() {
        return goodsNameCounter;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getAssembly() {
        return assembly;
    }

    public int getAssemblyCount() {
        return assemblyCount;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

}
