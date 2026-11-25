# Week 8 Notes: Properties and Bindings in JavaFX

## Activity 1: Observable Properties

1. **Why use StringProperty instead of a standard String?** A normal String cannot tell anyone when it changes. A StringProperty wraps the value, so the UI can listen to it or bind to it, and the UI updates automatically.
2. **Purpose of nameProperty():** It returns the property object itself, not the value. Other components need it so they can bind to the property or attach listeners, for example label.textProperty().bind(person.nameProperty()).

## Activity 2: ChangeListener vs InvalidationListener

| | InvalidationListener | ChangeListener |
|---|---|---|
| Tells me | Something changed | Something changed, plus the old value and new value |
| Good for | Triggering a refresh | Undo and redo, audit logs |

1. **Main difference:** An InvalidationListener only says the property is no longer valid. A ChangeListener also gives me the old and new values.
2. **Same value set again?** The listeners do not fire. JavaFX checks that the new value is different first.

## Activity 3: Unidirectional Binding

1. **What does unidirectional mean?** Data flows in one direction only. A change in the source (the TextField) updates the target (the Label), but a change in the target does not affect the source.
2. **What if I call setText() on a bound label?** It throws a RuntimeException: A bound value cannot be set. I must call unbind() first.

## Activity 4: Bidirectional Binding

1. **bind() vs bindBidirectional():** bind() only updates the target when the source changes. bindBidirectional() keeps both properties in sync, whichever one is changed.
2. **Why NumberStringConverter?** The Slider holds a number and the TextField holds a String. The converter changes the number into a String and back again.

## Activity 5: Reactive Registration Form

1. **Why bind disableProperty() instead of using an if statement in a key listener?** Bindings are declarative and automatic. I do not need code that checks on every keystroke. The code is cleaner and the button can never get out of sync with the fields.
2. **What does .not() do?** It flips the true or false value. In my form it makes the button disabled when the email does not contain an @ symbol.

**Example:** A Kenyan send-money form could keep its Send button disabled until the phone number and amount are valid, with no click handler for the validation.

## Bind vs Listener

- A **listener** runs code when a value changes.
- A **binding** links one value to another so it updates automatically.

## Final Self-Assessment

**Learning Outcome 1: Use JavaFX properties**
- [x] I can create a model using StringProperty and IntegerProperty.
- [x] I understand why properties need standard getters and property() getters.

**Learning Outcome 2: Implement listeners**
- [x] I know the difference between ChangeListener and InvalidationListener.
- [x] I can add a listener to a property.

**Learning Outcome 3: Create bindings**
- [x] I can bind a target property to a source property using bind().
- [x] I can create a bidirectional binding using bindBidirectional().

**Learning Outcome 4: Build a reactive UI**
- [x] I can bind a component's disable state to a condition, for example isEmpty().or(...).
- [x] I completed the Reactive Registration Form.