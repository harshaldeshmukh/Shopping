package com.example.hp.shopping.Model;

public class Specific_Model {
    public  String product_id,product,product_code,free_shipping,image_path,price;

    public Specific_Model(Builder builder) {
        product=builder.product;
        product_id=builder.product_id;
        product_code=builder.product_code;
        free_shipping=builder.free_shipping;
        image_path=builder.image_path;
        this.price=builder.price;
    }

    public  static  class  Builder{

        public  String product_id,product,product_code,free_shipping,image_path,price;

        public Builder setProduct_id(String product_id) {
            this.product_id = product_id;
            return  Builder.this;
        }

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

        public Builder setPrice(String price) {
            this.price = price;
            return  Builder.this;
        }

        public Specific_Model build() {
            return new Specific_Model(Builder.this);
        }
    }

}
