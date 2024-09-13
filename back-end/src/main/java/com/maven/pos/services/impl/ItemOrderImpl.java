package com.maven.pos.services.impl;

import com.maven.pos.entities.ItemOrders;
import com.maven.pos.entities.dto.SaleToppingStatus;
import com.maven.pos.repositories.IItemOrderRepository;
import com.maven.pos.services.IItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ItemOrderImpl implements IItemOrderService {

    @Autowired
    private IItemOrderRepository itemOrderRepository;
    @Override
    public ItemOrders addItemOrders(Long saleId, Long itemId, Long toppingId, Integer quantity) {

        ItemOrders itemOrders=new ItemOrders();
        itemOrders.setSaleId(saleId);
        itemOrders.setItemId(itemId);
        itemOrders.setToppingId(toppingId);
        itemOrders.setStatus(false);
        itemOrders.setQuantity(quantity);
        itemOrders.setItemOrderDate(LocalDate.now());
        return itemOrderRepository.save(itemOrders);
    }

    @Override
    public int updateOrderStatus(SaleToppingStatus saleToppingStatus) {

        if(saleToppingStatus.getTopping()!=null)
          return itemOrderRepository.updateStatusByItemIdAndToppingIdAndSaleId(true,saleToppingStatus.getItem().getItemId(),
                 saleToppingStatus.getTopping().getToppingId(),saleToppingStatus.getSale().getSaleId());
        else
            return itemOrderRepository.updateStatusByItemIdAndToppingIdAndSaleId(true,saleToppingStatus.getItem().getItemId(),
                null,saleToppingStatus.getSale().getSaleId());
    }

    @Override
    public void removeOrder(SaleToppingStatus saleToppingStatus) {

        itemOrderRepository.deleteItemOrder(saleToppingStatus.getSale().getSaleId(), saleToppingStatus.getItem().getItemId(),saleToppingStatus.getTopping().getToppingId());

    }
}
