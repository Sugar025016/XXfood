package com.xxxx.springsecuritydemo.entity;

import com.xxxx.springsecuritydemo.model.request.ProductRequest;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Product
 * @date: 2021/4/3
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "unit", length = 255)
    private String unit;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "origin_price", nullable = false)
    private Integer originPrice;

//    @Column(name="remark",length =255 )
//    private String remark;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_data_id", referencedColumnName = "id")
    private FileData fileData;

    @Column(name = "enabled")
    private boolean enabled;

//    @Column(name="enable")
//    private FileData image;

    @Column(name = "description", length = 255)
    private String description;


    @Column(name = "category", length = 255)
    private String category;


    @Column(name = "content", length = 255)
    private String content;


    @Column(name = "creat_date")
    private Date creatDate;


    public Product(ProductRequest productRequest, FileData fileData) {
        BeanUtils.copyProperties(productRequest, this);
        this.fileData = fileData;
        this.creatDate = new Date();
    }


    //    @Override
//    public String toString() {
//        return "Product{" +
//                "productId=" + productId +
//                ", name=" + name +
//                ", price=" + price +
//                ", originPrice=" + originPrice +
//                ", remark=" + remark  +
//                ", imagesId=" + imagesId +
//                ", enable=" + enable +
//                '}';
//    }


    public void setProduct(ProductRequest productRequest) {

//        this.title = productRequest.getProductName();
////        this.category = productRequest.get;
//        this.price = productRequest.getPrice();
//        this.originPrice = productRequest.getOriginPrice();
////        this.remark = productRequest.getRemark();
////        this.imagesId = productRequest.getImagesId();
//        this.enable = productRequest.isEnable();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", originPrice=" + originPrice +
                ", fileData=" + fileData +
                ", enabled=" + enabled +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", creatDate=" + creatDate +
                '}';
    }
}
