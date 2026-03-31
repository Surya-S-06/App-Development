# ShapeApp Documentation

## Project Overview
ShapeApp is a simple Android application built with Java that allows users to select a geometric shape and a color, and then displays the result on a second screen. The app demonstrates basic Android UI components, activity navigation, and custom view drawing.

## Design Components Used
The application utilizes Material Design components for a modern and consistent look.

*   **MaterialCardView**: Used to group related options (Shape Selection, Color Selection) with a card-like appearance, including elevation and rounded corners.
*   **RadioGroup & RadioButton**: Used for exclusive selection of shapes and colors.
*   **MaterialButton**: A styled button for the "Submit" action with rounded corners and elevation.
*   **TextView**: Used for headers and labels with bold typography.
*   **ScrollView**: Ensures the main content is scrollable on smaller screens.
*   **Custom View (ShapeView)**: A programmatic implementation of `View` to draw shapes dynamically using `Canvas` and `Paint`.

## Page Design Elements

### 1. Main Page (Selection Screen)
The first page serves as the input screen where the user customizes their shape.
*   **Header**: Displays the app name "ShapeApp" centered at the top.
*   **Shape Selection Card**: A `MaterialCardView` containing a `RadioGroup` with options:
    *   Circle
    *   Square
    *   Triangle
*   **Color Selection Card**: A second `MaterialCardView` containing a `RadioGroup` with options:
    *   Red
    *   Blue
    *   Green
    *   Yellow
*   **Submit Button**: A large, centered `MaterialButton` at the bottom. When clicked, it validates the selection and navigates to the next page, passing the selected shape and color as intent extras.

### 2. Second Page (Display Screen)
The second page displays the visual result of the user's selection.
*   **Title**: displays "Result" at the top.
*   **Shape Container**: A `MaterialCardView` that acts as a frame for the result.
*   **Dynamic Shape**: Inside the container, a custom `ShapeView` class renders the selected shape (Circle, Square, or Triangle) in the selected color using Android's 2D graphics API (`Canvas.drawCircle`, `Canvas.drawRect`, `Canvas.drawPath`).

## Technical Implementation
*   **Language**: Java
*   **Minimum SDK**: 24 (Android 7.0)
*   **Target SDK**: 33 (Android 13)
*   **Build System**: Gradle 8.0
