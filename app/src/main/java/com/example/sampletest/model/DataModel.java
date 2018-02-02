package com.example.sampletest.model;

import java.util.List;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class DataModel {

    private List<CategoriesBean> categories;
    private List<RankingsBean> rankings;

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<RankingsBean> getRankings() {
        return rankings;
    }

    public void setRankings(List<RankingsBean> rankings) {
        this.rankings = rankings;
    }

    public static class CategoriesBean {
        /**
         * id : 1
         * name :  Casuals
         * products : [{"id":1,"name":"Nike Sneakers","date_added":"2016-12-09T11:16:11.000Z","variants":[{"id":1,"color":"Blue","size":42,"price":1000},{"id":2,"color":"Red","size":42,"price":1000},{"id":3,"color":"Blue","size":44,"price":1200},{"id":4,"color":"Red","size":44,"price":1200}],"tax":{"name":"VAT","value":12.5}},{"id":2,"name":"Adidas Running Shoes","date_added":"2016-11-05T11:16:11.000Z","variants":[{"id":5,"color":"White","size":40,"price":2000},{"id":6,"color":"Black","size":40,"price":2000},{"id":7,"color":"White","size":44,"price":2200},{"id":8,"color":"Red","size":44,"price":2200}],"tax":{"name":"VAT4","value":4}},{"id":21,"name":"Roadster Loafers","date_added":"2016-01-18T11:16:11.000Z","variants":[{"id":65,"color":"Black","size":44,"price":3500},{"id":66,"color":"Blue","size":44,"price":3200}],"tax":{"name":"VAT4","value":4}},{"id":22,"name":"Light Loafers","date_added":"2016-01-18T11:16:11.000Z","variants":[{"id":67,"color":"Blue","size":42,"price":2800},{"id":68,"color":"Yellow","size":42,"price":2800}],"tax":{"name":"VAT4","value":4}},{"id":23,"name":"Floaters","date_added":"2016-01-18T11:16:11.000Z","variants":[{"id":69,"color":"Black","size":40,"price":3500},{"id":70,"color":"Red","size":40,"price":3500}],"tax":{"name":"VAT4","value":4}}]
         * child_categories : []
         */

        private int id;
        private String name;
        private List<ProductsBean> products;
        private List<Object> child_categories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public List<Object> getChild_categories() {
            return child_categories;
        }

        public void setChild_categories(List<Object> child_categories) {
            this.child_categories = child_categories;
        }

        public static class ProductsBean {
            /**
             * id : 1
             * name : Nike Sneakers
             * date_added : 2016-12-09T11:16:11.000Z
             * variants : [{"id":1,"color":"Blue","size":42,"price":1000},{"id":2,"color":"Red","size":42,"price":1000},{"id":3,"color":"Blue","size":44,"price":1200},{"id":4,"color":"Red","size":44,"price":1200}]
             * tax : {"name":"VAT","value":12.5}
             */

            private int id;
            private String name;
            private String date_added;
            private TaxBean tax;
            private List<VariantsBean> variants;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDate_added() {
                return date_added;
            }

            public void setDate_added(String date_added) {
                this.date_added = date_added;
            }

            public TaxBean getTax() {
                return tax;
            }

            public void setTax(TaxBean tax) {
                this.tax = tax;
            }

            public List<VariantsBean> getVariants() {
                return variants;
            }

            public void setVariants(List<VariantsBean> variants) {
                this.variants = variants;
            }

            public static class TaxBean {
                /**
                 * name : VAT
                 * value : 12.5
                 */

                private String name;
                private double value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }
            }

            public static class VariantsBean {
                /**
                 * id : 1
                 * color : Blue
                 * size : 42
                 * price : 1000
                 */

                private int id;
                private String color;
                private int size;
                private int price;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }
            }
        }
    }

    public static class RankingsBean {
        /**
         * ranking : Most Viewed Products
         * products : [{"id":1,"view_count":56700},{"id":2,"view_count":60000},{"id":3,"view_count":74000},{"id":4,"view_count":12000},{"id":5,"view_count":66000},{"id":6,"view_count":23456},{"id":7,"view_count":65783},{"id":8,"view_count":23456},{"id":9,"view_count":78965},{"id":10,"view_count":23456},{"id":11,"view_count":65784},{"id":12,"view_count":34756},{"id":13,"view_count":67543},{"id":14,"view_count":20000},{"id":15,"view_count":35000},{"id":16,"view_count":22000},{"id":17,"view_count":21000},{"id":18,"view_count":28000},{"id":19,"view_count":87694},{"id":20,"view_count":78645},{"id":21,"view_count":54673},{"id":22,"view_count":76894},{"id":23,"view_count":34567},{"id":24,"view_count":23456},{"id":25,"view_count":54678}]
         */

        private String ranking;
        private List<ProductsBeanX> products;

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public List<ProductsBeanX> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBeanX> products) {
            this.products = products;
        }

        public static class ProductsBeanX {
            /**
             * id : 1
             * view_count : 56700
             */

            private int id;
            private int view_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getView_count() {
                return view_count;
            }

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }
        }
    }
}
