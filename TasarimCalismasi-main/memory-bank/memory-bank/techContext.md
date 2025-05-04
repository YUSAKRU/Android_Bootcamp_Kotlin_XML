# Technical Context: Android Ecommerce Screen

## Technology Stack
- **Programming Language**: Kotlin
- **UI Framework**: Android XML Layouts
- **Layout System**: ConstraintLayout, CardView, NestedScrollView
- **Design Tools**: Figma for design mockups
- **Asset Integration**: Figma MCP for extracting assets

## Development Environment
- **IDE**: Android Studio (inferred)
- **Build System**: Gradle (Kotlin DSL)
- **Version Control**: Git
- **API Level**: Targeting modern Android versions

## Deployment Process
- Standard Android app deployment through APK or App Bundle
- Testing on multiple device sizes to ensure responsive design

## Performance Considerations
- **Image Optimization**: Using appropriate image compression and loading
- **Scrolling Performance**: Using RecyclerView pattern concepts for horizontal scrolling
- **Layout Hierarchy**: Minimizing nested layouts where possible
- **Memory Usage**: Efficient asset management for images and resources

## Technical Debt
- Current implementation uses static content only (no dynamic data)
- Search bar is non-functional (visual only)
- Bottom navigation has no actual navigation logic
- Navigation only works one-way (from main to ecommerce)

## Resource Management
- **Drawables**: 
  - Product images extracted from Figma
  - Icon assets for UI elements
  - Shape drawables for UI components
- **Layouts**:
  - activity_ecommerce.xml for the main layout
  - Supporting layouts for components as needed
- **Values**:
  - strings.xml for text content
  - colors.xml for color definitions
  - styles.xml for reusable styles