package org.example.chapter05.client;

import org.example.chapter05.domain.Item;
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
        System.out.println("주문 날짜 : " + order.getOrderDate());
        System.out.println("[주문 목록]");

        List<Item> itemList = order.getItemList();
        for (Item item : itemList) {
            System.out.println("---> " + item.getProduct().getName());
        }
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 상품 등록
        Product product1 = new Product();
        product1.setName("LG 통돌이 세탁기");
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("갤럭시 20");
        em.persist(product2);

        // 주문 등록
        Order order = new Order();
        order.setOrderDate(new Date());
        em.persist(order);

        // 카트 등록
        Item item1 = new Item();
        item1.setOrder(order);
        item1.setProduct(product1);
        item1.setPrice(100_000L);
        em.persist(item1);

        Item item2 = new Item();
        item2.setOrder(order);
        item2.setProduct(product2);
        item2.setPrice(270_000L);
        item2.setQuantity(3L);
        em.persist(item2);

        em.getTransaction().commit();
        em.close();
    }
}
