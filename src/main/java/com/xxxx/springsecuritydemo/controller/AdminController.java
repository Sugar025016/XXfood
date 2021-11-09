package com.xxxx.springsecuritydemo.controller;

import com.xxxx.springsecuritydemo.entity.FileData;
import com.xxxx.springsecuritydemo.entity.Product;
import com.xxxx.springsecuritydemo.entity.Schedule;
import com.xxxx.springsecuritydemo.enums.Rcode;
import com.xxxx.springsecuritydemo.enums.ScheduleType;
import com.xxxx.springsecuritydemo.model.request.ProductRequest;
import com.xxxx.springsecuritydemo.model.request.ScheduleRequest;
import com.xxxx.springsecuritydemo.model.Response;
import com.xxxx.springsecuritydemo.model.request.SchedulesRequest;
import com.xxxx.springsecuritydemo.model.response.FileResponse;
import com.xxxx.springsecuritydemo.model.response.ProductResponse;
import com.xxxx.springsecuritydemo.model.response.SchedulesResponse;
import com.xxxx.springsecuritydemo.service.Impl.FileService;
import com.xxxx.springsecuritydemo.service.Impl.ProductService;
import com.xxxx.springsecuritydemo.service.Impl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: AdminController
 * @date: 2021/9/17
 * @description:
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ProductService productService;

    @Autowired
    FileService fileService;

    @Value("${imageGetUrl}")
    String imageGetUrl;
    @Value("${imagePutUrl}")
    String imagePutUrl;

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public Response check() {
        System.out.println("有鬼阿..........=.=+，");

        return new Response(Rcode.Success);
    }

    @RequestMapping(path = "/aa", method = RequestMethod.GET)
    public Response getaa() {
        System.out.println("有鬼阿..........=.=+，");

        return new Response(Rcode.Success, "aa");
    }


    @RequestMapping(path = "/schedule", method = RequestMethod.GET)
    public Response getSchedule(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date date, Authentication authentication) {

        System.out.println("authentication：");
        System.out.println(authentication.getPrincipal());
        System.out.println(date);
        List<Schedule> scheduleList = scheduleService.getSchedule(date);


        return new Response(Rcode.Success, scheduleList);
    }


//    @RequestMapping(path="/schedules", method = RequestMethod.GET )
//    public Response getSchedules(SchedulRequest schedulRequest){
//
//
//        return new Response(Rcode.Success,"starDate");
//    }


    @RequestMapping(path = "/schedules", method = RequestMethod.GET)
    public Response getSchedules(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date endDate) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
            System.out.println("成功********"+((UserDetails) principal).getUsername());
        }

        if (principal instanceof Principal) {
//            return ((Principal) principal).getName();
            System.out.println("成功********"+((Principal) principal).getName());
        }

        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(principal));
        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(SecurityContextHolder.getContext().getAuthentication().getDetails()));
        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(SecurityContextHolder.getContext().getAuthentication().getCredentials()));
        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
//        System.out.println("成功********"+"當前登入使用者：" + String.valueOf(SecurityContextHolder.getContext().getAuthentication().()));
//        return "當前登入使用者：" + String.valueOf(principal);

        List<SchedulesResponse> scheduleList = scheduleService.getSchedules(startDate, endDate);
        System.out.println("成功********");
        return new Response(Rcode.Success, scheduleList);
    }


    @RequestMapping(path = "/schedule", method = RequestMethod.PUT)
    public Response putSchedule(@RequestBody() ScheduleRequest scheduleRequest) {

        System.out.println("成功********");
        System.out.println(scheduleRequest);
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
        scheduleRequest.getOpenTime().forEach(v -> {
            Schedule schedule = new Schedule(scheduleRequest.getDate(), v, ScheduleType.OpenTime.getKey());
            scheduleArrayList.add(schedule);
        });
        scheduleRequest.getOutTime().forEach(v -> {
            Schedule schedule = new Schedule(scheduleRequest.getDate(), v, ScheduleType.OutTime.getKey());
            scheduleArrayList.add(schedule);
        });
        scheduleService.saveAll(scheduleArrayList, scheduleRequest.getDate());

        return new Response(Rcode.Success);
    }


    @RequestMapping(path = "/schedules", method = RequestMethod.PUT)
    public Response putSchedules(@RequestBody() SchedulesRequest schedulesRequest) {

//        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
        System.out.println("成功********");
        System.out.println(schedulesRequest);
        scheduleService.saveAll(schedulesRequest);


        return new Response(Rcode.Success);
    }


    @RequestMapping(path = "/product", method = RequestMethod.POST)
    public Response postProduct(@RequestBody() ProductRequest productRequest) {

//        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
        System.out.println("成功********");
        System.out.println(productRequest);
        productService.addProduct(productRequest);
//        FileData one = fileService.getOne(productRequest.getImageId());
//        Product product = new Product(productRequest,one);
//        productService.saveProduct(product);

        return new Response(Rcode.Success);
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public Response getProducts(@PageableDefault(size = 10, page = 0) Pageable pageable) {

//        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
        System.out.println("成功********");
        System.out.println(pageable);
        Page<ProductResponse> productPage = productService.getProduct(pageable);
        productPage.getContent().stream().forEach(v -> {
            v.setImageUrl(v.getImageUrl() != null ? imageGetUrl + v.getImageUrl() : null);
        });

        return new Response(Rcode.Success, productPage);
    }


//    @RequestMapping(path="/product", method = RequestMethod.GET )
//    public Response getProduct(){
//
////        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
//        System.out.println("成功********");
//        System.out.println(pageable);
//        Page<ProductResponse> productPage = productService.getProduct(pageable);
//
//        return new Response(Rcode.Success,productPage);
//    }


    @RequestMapping(path = "/product/{id}", method = RequestMethod.PUT)
    public Response putProduct(@RequestBody() ProductRequest productRequest, @PathVariable() int id) {

//        List<SchedulesResponse> scheduleList = sckeduleService.getSchedules(startDate,endDate);
        System.out.println("成功********");
        System.out.println(productRequest);
        Product product1 = productService.changeProduct(productRequest, id);
        return new Response(Rcode.Success);
    }


    @RequestMapping(path = "/product/{id}", method = RequestMethod.DELETE)
    public Response deleteProduct(@PathVariable() int id ) {

        productService.deleteProduct(id);
        return new Response(Rcode.Success);
    }


    @Transactional
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public Response uploadFile(MultipartFile uploadedFile) {

        System.out.println(uploadedFile.getName());
        System.out.println("uploadedFile.getContentType():" + uploadedFile.getContentType());

        System.out.println("uploadedFile.getOriginalFilename():" + uploadedFile.getOriginalFilename());
        System.out.println("成功********");

        FileData save = fileService.save(uploadedFile);

        FileResponse fileResponse = new FileResponse(save.getId(), imageGetUrl + save.getFileName());
        return new Response(Rcode.Success, fileResponse);
    }


}