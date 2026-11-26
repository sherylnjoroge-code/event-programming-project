# Activity 1: Spot the Components

| System | Event Source | Event | Listener | Handler/Action |
|---|---|---|---|---|
| ATM | Keypad button | Button pressed | ATM software | Verify PIN |
| ATM | Card slot | Card inserted | Card reader | Read card data |
| ATM | Cash dispenser | Cash taken | Sensor | End transaction |
| Microwave oven | Start button | Start pressed | Control board | Turn on heating and start timer |
| Microwave oven | Door switch | Door opened | Door sensor | Stop heating |
| Traffic light | Timer | Timer expires | Light controller | Change the light colour |
| Video game | Keyboard or controller | Key pressed | Game engine | Move the character |
| Elevator | Floor button | Button pressed | Elevator controller | Add floor to the queue and move |
| M-Pesa STK push | Phone keypad | PIN entered | M-Pesa app | Send money and show confirmation |
| Jumia website | Place Order button | Button clicked | Checkout code | Confirm the order and send an SMS |
| Smartphone touchscreen | Screen | Screen tapped | Phone operating system | Open the app that was tapped |
| Supermarket self-checkout | Barcode scanner | Item scanned | Till software | Add the item to the bill |

## Self-Check

1. **Event source vs event listener:** The event source is the thing that creates the event, like a button. The event listener is the thing that waits for that event and reacts when it happens.
2. **Can one event source have multiple listeners?** Yes. One Place Order click on Jumia can update the stock, send an SMS and save the order, all by different listeners.
3. **Can one listener handle events from multiple sources?** Yes. In my calculator, one number handler is used by all the number buttons from 0 to 9.