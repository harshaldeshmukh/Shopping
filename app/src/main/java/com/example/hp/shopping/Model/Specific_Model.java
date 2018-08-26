package com.example.hp.shopping.Model;

public class Specific_Model {
    public  String product,product_code,free_shipping,image_path;

    public Specific_Model(Builder builder) {
        product=builder.product;
        product_code=builder.product_code;
        free_shipping=builder.free_shipping;
        image_path=builder.image_path;
    }

    public  static  class  Builder{

        public  String product,product_code,free_shipping,image_path;

        public Builder setProduct(String product) {
            this.product = product;
            return  Builder.this;
        }

        public Builder setProduct_code(String product_code) {
            this.product_code = product_code;
            return  Builder.this;
        }

        public Builder setFree_shipping(String free_shipping) {
            this.free_shipping = free_shipping;
            return  Builder.this;
        }

        public Builder setImage_path(String image_path) {
            this.image_path = image_path;
            return  Builder.this;
        }

        public Specific_Model build() {
            return new Specific_Model(Builder.this);
        }
    }

}
