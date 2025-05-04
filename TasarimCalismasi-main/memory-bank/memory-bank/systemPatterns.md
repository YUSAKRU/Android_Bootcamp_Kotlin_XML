# System Patterns: Android Ecommerce Screen

## System Architecture
The application follows standard Android architecture patterns:
- Activity-based navigation
- XML layouts for UI design
- Kotlin for application logic
- Resource-based approach for strings, colors, and drawables

## Component Diagrams
1. **Main Activity Structure**
   ```
   MainActivity
   ├── Toolbar with menu
   ├── Pizza content
   └── Navigation to EcommerceActivity
   ```

2. **Ecommerce Activity Structure**
   ```
   EcommerceActivity
   ├── Search Bar
   ├── ScrollView Content
   │   ├── Categories Section
   │   ├── Popular Products Section
   │   ├── Banner
   │   ├── Pills Section
   │   └── Recent Products Section
   └── Bottom Navigation Bar
   ```

## Design Patterns Used
- **Activity-Intent Pattern**: For navigation between screens
- **ViewHolder Pattern**: For efficient list rendering (in horizontal scrolls)
- **Resource Management Pattern**: Using Android resource system for strings, layouts, and drawables
- **Constraint-based Layout**: Using ConstraintLayout for responsive UI design

## Integration Points
- MainActivity to EcommerceActivity navigation via menu option
- Shared resources (drawables, colors, strings)
- Potential future integration with data providers for product information

## Data Flow
1. User initiates navigation from MainActivity to EcommerceActivity
2. EcommerceActivity loads and displays static content
3. User can interact with UI elements:
   - Scroll through categories
   - Browse product cards
   - View promotional banner
   - Access quick action pills
   - Use bottom navigation