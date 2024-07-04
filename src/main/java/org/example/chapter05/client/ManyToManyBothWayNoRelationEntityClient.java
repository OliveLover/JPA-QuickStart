package org.example.chapter05.client;

import org.example.chapter05.domain.Order;
import org.example.chapter05.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class ManyToManyBothWayNoRelationEntityClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter05");
        try {
            dataInsert(emf);
            dataSelect(emf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        // 검색한 Order를 통해 Product 목록을 출력한다.
        Order order = em.find(Order.class, 1L);
        System.out.println(order.getId() + "번 주문에 대한 상품 목록");

        List<Product> productList = order.getProductList();
        for (Product product : productList) {
            System.out.println("---> " + product.getName());
        }

        // 검색한 Product를 통해 Order 목록을 출력한다.
        Product product = em.find(Product.class, 1L);

        System.out.println(product.getName() + " 상품에 대한 주문 정보");
        List<Order> orderList = product.getOrderList();
        for (Order ord : orderList) {
            System.out.println("---> " + ord.toString());
        }
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 1번 상품 목록
        Product product1 = new Product();
        product1.setName("LG 통돌이 세탁기");
        em.persist(product1);

        // 2번 상품 등록
        Product product2 = new Product();
        product2.setName("다이슨 청소기");
        em.persist(product2);

        // 1번 주문 등록
        Order order1 = new Order();
        order1.setOrderDate(new Date());
        order1.addProduct(product1);
        em.persist(order1);

        // 2번 주문 등록
        Order order2 = new Order();
        order2.setOrderDate(new Date());
        order2.addProduct(product1);
        em.persist(order2);

        em.getTransaction().commit();
        em.close();
    }
}
