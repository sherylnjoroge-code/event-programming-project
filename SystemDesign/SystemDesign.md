# Event-Driven System Design

## System Name: Restaurant Order System

---

## Part 1: Event Sources

1. Waiter's tablet - generates "order placed" and "item added" events
2. Kitchen screen - generates "order ready" events
3. Cashier till - generates "payment received" events (cash or M-Pesa)
4. Customer's phone - generates "M-Pesa payment confirmed" events
5. System timer - generates "order delayed" events when food takes too long

---

## Part 2: Events

| Event Name | Source | Description |
|---|---|---|
| OrderPlaced | Waiter's tablet | Waiter submits a new order for a table |
| OrderReady | Kitchen screen | Chef marks an order as cooked |
| PaymentReceived | Cashier till | Customer pays by cash or M-Pesa |
| MpesaConfirmed | Customer's phone | Safaricom confirms the payment went through |
| OrderDelayed | System timer | An order has waited more than 20 minutes |

---

## Part 3: Event Handlers

| Event | Handler Action |
|---|---|
| OrderPlaced | Add the order to the kitchen queue and show it on the kitchen screen |
| OrderReady | Remove it from the kitchen queue and alert the waiter to serve |
| PaymentReceived | Record the payment, print the receipt and free the table |
| MpesaConfirmed | Mark the bill as paid and send an SMS receipt to the customer |
| OrderDelayed | Show a warning to the manager and notify the waiter |

---

## Part 4: Event Flow Diagram

```
Waiter taps "Place Order"
        |
        v
Event Source (Waiter's tablet)
        |
        v
Event Object (OrderPlaced: table 4, ugali and beef)
        |
        v
Event Queue (orders wait in line)
        |
        v
Event Loop (picks the next event)
        |
        v
Handler (add order to kitchen queue)
        |
        v
GUI Update (kitchen screen shows the new order)
```

---

## Part 5: Potential Issues

1. Slow handler freezes the screen (for example, a slow payment check) - Run slow work like payment checks in a background thread and update the screen on the EDT.
2. Two waiters send orders at the same time and the order gets mixed up - Use an event queue so orders are handled one by one, in the order they arrive.
3. M-Pesa confirmation never arrives because of poor network - Add a timeout event and let the cashier confirm the payment manually.