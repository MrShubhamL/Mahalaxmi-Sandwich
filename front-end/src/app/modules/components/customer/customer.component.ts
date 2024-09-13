import { AfterContentInit, Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit {

  constructor(private service: AdminService){}

  orders = [
    {
      billNumber: "",
      itemName: ""
    }
  ]

  pending_orders = [
    {
      billNumber: "",
      itemName: ""
    }
  ]

  ngOnInit(): void {
    this.service.getCompletedOrders().subscribe(res =>{
      this.orders = res;
    },err=>{
      console.log(err);
    })

    this.service.getPendingOrdersKitchen().subscribe(res =>{
      this.pending_orders = res;
    },err=>{
      console.log(err);
    })


  }

}
