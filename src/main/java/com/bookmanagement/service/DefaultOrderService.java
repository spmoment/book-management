package com.bookmanagement.service;

import com.bookmanagement.dto.CreateOrderRequest;
import com.bookmanagement.entity.Books;
import com.bookmanagement.entity.OrderItem;
import com.bookmanagement.entity.Orders;
import com.bookmanagement.entity.Users;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.repository.BooksRepository;
import com.bookmanagement.repository.OrderItemRepository;
import com.bookmanagement.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.bookmanagement.enums.EnumStatus.NEW;
import static java.util.Objects.isNull;

@Service
public class DefaultOrderService implements OrderService {

    public static final String DUMMY = "default_password";
    private static final Logger log = Logger.getLogger("DefaultOrderService.class");

    private OrdersRepository ordersRepository;
    private BooksRepository booksRepository;
    private UserService userService;
    private OrderItemRepository orderItemRepository;

    public DefaultOrderService(OrdersRepository ordersRepository, BooksRepository booksRepository,
                               UserService userService, OrderItemRepository orderItemRepository) {
        this.ordersRepository = ordersRepository;
        this.booksRepository = booksRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Integer saveOrder(CreateOrderRequest request) throws ValidationException {

        Orders orderEntity = new Orders();
        orderEntity.setStatus(NEW);
        orderEntity.setAddress(request.getAddress());
        orderEntity.setUsers(prepareUser(request));

        log.info("saving order: " + orderEntity);

        orderEntity = ordersRepository.save(orderEntity);

        log.info("preparing order items");

        Map<Integer, Integer> bookIdBookCount = prepareOrderItems(request);

        log.info("saving order items: " + bookIdBookCount);

        saveOrderItems(orderEntity, bookIdBookCount);

        return orderEntity.getId();
    }

    private Map<Integer, Integer> prepareOrderItems(CreateOrderRequest request) {
        Map<Integer, Integer> bookIdBookCount = new HashMap<>();

        request.getBookIds().forEach(bookId -> {
            if (bookIdBookCount.containsKey(bookId)) {
                Integer bookCount = bookIdBookCount.get(bookId);
                bookCount = bookCount + 1;
                bookIdBookCount.put(bookId, bookCount);
            } else {
                bookIdBookCount.put(bookId, 1);
            }

        });
        return bookIdBookCount;
    }

    private void saveOrderItems(Orders orderEntity, Map<Integer, Integer> bookIdBookCount) {
        for (Map.Entry<Integer, Integer> entry : bookIdBookCount.entrySet()) {
            Integer bookId = entry.getKey();
            Integer bookCount = entry.getValue();
            Books books = booksRepository.findBookById(bookId);
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(books);
            orderItem.setCount(bookCount);
            orderItem.setOrder(orderEntity);
            orderItemRepository.save(orderItem);
        }
    }

    private Users prepareUser(CreateOrderRequest request) throws ValidationException {
        log.info("Preparing user with phonenumber: " + request.getPhoneNumber());
        validateCreateOrderRequest(request);
        Users user = userService.findByPhoneNumber(request.getPhoneNumber());
        if (user == null) {
            user = new Users();
            user.setPhoneNumber(request.getPhoneNumber());
            user.setPassword(DUMMY);
            user = userService.saveUser(user);

        }
        return user;
    }

    private void validateCreateOrderRequest(CreateOrderRequest request) throws ValidationException {
        if (isNull(request.getPhoneNumber()) && isNull(request.getAddress())) {
            throw new ValidationException("Phone Number and address is null");
        } else if (isNull(request.getPhoneNumber())) {
            throw new ValidationException("Phone Number is null");
        } else if (isNull(request.getAddress())) {
            throw new ValidationException("Address is null");
        }
    }
}
