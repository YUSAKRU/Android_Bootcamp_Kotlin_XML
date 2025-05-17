## Technical Framework

### Project Structure
- Android application using Kotlin and MVVM architecture
- Uses Android Jetpack Navigation Component for navigation between fragments

### UI Components
- Uses XML layouts for UI design
- RecyclerView for listing food items
- ConstraintLayout for most screens
- Lottie for animations

### Dependencies
- **Retrofit** - For API communication
- **OkHttp** - HTTP client for API calls
- **Glide** - Image loading library
- **Lottie** - Animation library from Airbnb
- **Kotlin Coroutines** - For asynchronous programming
- **ViewModel & LiveData** - For implementing MVVM architecture

### Lottie Animation Considerations
- Lottie has different support levels across platforms (Android, iOS, Web)
- Several Lottie features can cause issues on Android:
  - **PolystarShape components**: The original error was a NullPointerException involving a PolystarShape with a missing Type property. The "sr" shape type in the JSON file was not properly configured.
  - Masks and mattes: Complex masking can be problematic
  - Visual effects like blurs and gradients: May not render correctly
  - Expressions and script-based animations: Not supported on mobile
- Best practices:
  - Use simple animations with paths, basic shapes, and trim paths
  - Avoid star shapes or ensure they have all required properties
  - Test animations thoroughly on target platforms before integration
  - Consider using simple SVG animations converted to Lottie format
  - Maintain fallback options for complex animations

### API Integration
- RESTful API communication pattern
- Uses Retrofit with OkHttp for network calls
- API responses are parsed to Kotlin data classes using Gson
- Error handling for different response codes and formats