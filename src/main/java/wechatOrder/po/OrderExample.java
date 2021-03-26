package wechatOrder.po;

import java.util.ArrayList;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdIsNull() {
            addCriterion("deliveryman_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdIsNotNull() {
            addCriterion("deliveryman_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdEqualTo(Integer value) {
            addCriterion("deliveryman_id =", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdNotEqualTo(Integer value) {
            addCriterion("deliveryman_id <>", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdGreaterThan(Integer value) {
            addCriterion("deliveryman_id >", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliveryman_id >=", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdLessThan(Integer value) {
            addCriterion("deliveryman_id <", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdLessThanOrEqualTo(Integer value) {
            addCriterion("deliveryman_id <=", value, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdIn(List<Integer> values) {
            addCriterion("deliveryman_id in", values, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdNotIn(List<Integer> values) {
            addCriterion("deliveryman_id not in", values, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdBetween(Integer value1, Integer value2) {
            addCriterion("deliveryman_id between", value1, value2, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andDeliverymanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deliveryman_id not between", value1, value2, "deliverymanId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andPatternIsNull() {
            addCriterion("pattern is null");
            return (Criteria) this;
        }

        public Criteria andPatternIsNotNull() {
            addCriterion("pattern is not null");
            return (Criteria) this;
        }

        public Criteria andPatternEqualTo(String value) {
            addCriterion("pattern =", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotEqualTo(String value) {
            addCriterion("pattern <>", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThan(String value) {
            addCriterion("pattern >", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThanOrEqualTo(String value) {
            addCriterion("pattern >=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThan(String value) {
            addCriterion("pattern <", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThanOrEqualTo(String value) {
            addCriterion("pattern <=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLike(String value) {
            addCriterion("pattern like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotLike(String value) {
            addCriterion("pattern not like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternIn(List<String> values) {
            addCriterion("pattern in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotIn(List<String> values) {
            addCriterion("pattern not in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternBetween(String value1, String value2) {
            addCriterion("pattern between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotBetween(String value1, String value2) {
            addCriterion("pattern not between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(String value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(String value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(String value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(String value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLike(String value) {
            addCriterion("payment_method like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotLike(String value) {
            addCriterion("payment_method not like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<String> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<String> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(String value1, String value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(String value1, String value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andLeaveWordIsNull() {
            addCriterion("leave_word is null");
            return (Criteria) this;
        }

        public Criteria andLeaveWordIsNotNull() {
            addCriterion("leave_word is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveWordEqualTo(String value) {
            addCriterion("leave_word =", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordNotEqualTo(String value) {
            addCriterion("leave_word <>", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordGreaterThan(String value) {
            addCriterion("leave_word >", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordGreaterThanOrEqualTo(String value) {
            addCriterion("leave_word >=", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordLessThan(String value) {
            addCriterion("leave_word <", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordLessThanOrEqualTo(String value) {
            addCriterion("leave_word <=", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordLike(String value) {
            addCriterion("leave_word like", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordNotLike(String value) {
            addCriterion("leave_word not like", value, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordIn(List<String> values) {
            addCriterion("leave_word in", values, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordNotIn(List<String> values) {
            addCriterion("leave_word not in", values, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordBetween(String value1, String value2) {
            addCriterion("leave_word between", value1, value2, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andLeaveWordNotBetween(String value1, String value2) {
            addCriterion("leave_word not between", value1, value2, "leaveWord");
            return (Criteria) this;
        }

        public Criteria andServicingTimeIsNull() {
            addCriterion("servicing_time is null");
            return (Criteria) this;
        }

        public Criteria andServicingTimeIsNotNull() {
            addCriterion("servicing_time is not null");
            return (Criteria) this;
        }

        public Criteria andServicingTimeEqualTo(String value) {
            addCriterion("servicing_time =", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeNotEqualTo(String value) {
            addCriterion("servicing_time <>", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeGreaterThan(String value) {
            addCriterion("servicing_time >", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("servicing_time >=", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeLessThan(String value) {
            addCriterion("servicing_time <", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeLessThanOrEqualTo(String value) {
            addCriterion("servicing_time <=", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeLike(String value) {
            addCriterion("servicing_time like", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeNotLike(String value) {
            addCriterion("servicing_time not like", value, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeIn(List<String> values) {
            addCriterion("servicing_time in", values, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeNotIn(List<String> values) {
            addCriterion("servicing_time not in", values, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeBetween(String value1, String value2) {
            addCriterion("servicing_time between", value1, value2, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andServicingTimeNotBetween(String value1, String value2) {
            addCriterion("servicing_time not between", value1, value2, "servicingTime");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNull() {
            addCriterion("goods_amount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNotNull() {
            addCriterion("goods_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountEqualTo(Double value) {
            addCriterion("goods_amount =", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotEqualTo(Double value) {
            addCriterion("goods_amount <>", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThan(Double value) {
            addCriterion("goods_amount >", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_amount >=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThan(Double value) {
            addCriterion("goods_amount <", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThanOrEqualTo(Double value) {
            addCriterion("goods_amount <=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIn(List<Double> values) {
            addCriterion("goods_amount in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotIn(List<Double> values) {
            addCriterion("goods_amount not in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountBetween(Double value1, Double value2) {
            addCriterion("goods_amount between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotBetween(Double value1, Double value2) {
            addCriterion("goods_amount not between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andFreightChargeIsNull() {
            addCriterion("freight_charge is null");
            return (Criteria) this;
        }

        public Criteria andFreightChargeIsNotNull() {
            addCriterion("freight_charge is not null");
            return (Criteria) this;
        }

        public Criteria andFreightChargeEqualTo(Double value) {
            addCriterion("freight_charge =", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeNotEqualTo(Double value) {
            addCriterion("freight_charge <>", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeGreaterThan(Double value) {
            addCriterion("freight_charge >", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeGreaterThanOrEqualTo(Double value) {
            addCriterion("freight_charge >=", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeLessThan(Double value) {
            addCriterion("freight_charge <", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeLessThanOrEqualTo(Double value) {
            addCriterion("freight_charge <=", value, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeIn(List<Double> values) {
            addCriterion("freight_charge in", values, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeNotIn(List<Double> values) {
            addCriterion("freight_charge not in", values, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeBetween(Double value1, Double value2) {
            addCriterion("freight_charge between", value1, value2, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andFreightChargeNotBetween(Double value1, Double value2) {
            addCriterion("freight_charge not between", value1, value2, "freightCharge");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(Double value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(Double value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(Double value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(Double value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(Double value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<Double> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<Double> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(Double value1, Double value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(Double value1, Double value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(String value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(String value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(String value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(String value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(String value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(String value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLike(String value) {
            addCriterion("order_time like", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotLike(String value) {
            addCriterion("order_time not like", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<String> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<String> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(String value1, String value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(String value1, String value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(String value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(String value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(String value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(String value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(String value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLike(String value) {
            addCriterion("pay_time like", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotLike(String value) {
            addCriterion("pay_time not like", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<String> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<String> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(String value1, String value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(String value1, String value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}