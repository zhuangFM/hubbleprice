public class Coupon {
    /**
     * 目标金额 满xx
     */
    private Double targetMoney;

    /**
     * 减少金额 减xx
     */
    private Double reduceMoney;

    /**
     * 优惠券类型
     * 1--满减 2--每满减
     */
    private Integer couponType;

    public Coupon(Double targetMoney, Double reduceMoney, Integer couponType) {
        this.targetMoney = targetMoney;
        this.reduceMoney = reduceMoney;
        this.couponType = couponType;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(Double reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Double getResultMoney(Double srcMoney) {
        if (srcMoney < this.targetMoney) {
            return srcMoney;
        } else {
            switch (this.couponType) {
                case 1:
                    return srcMoney - this.reduceMoney;
                case 2:
                    return srcMoney - (int)(srcMoney / this.targetMoney) * this.reduceMoney;
                default:
                    return srcMoney;
            }
        }
    }

    @Override
    public String toString() {
        if(this.couponType == 1){
            return "满"+this.targetMoney+"减"+this.reduceMoney;
        }
        else {
            return "每满"+this.targetMoney+"减"+this.reduceMoney;
        }
    }
}
