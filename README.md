# 🍯 Jenu-Gumpu — Honey Producer's Collective App

![Android](https://img.shields.io/badge/Platform-Android-green) ![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue) ![Room DB](https://img.shields.io/badge/Database-Room-orange) ![Status](https://img.shields.io/badge/Status-Completed-success)

## 📌 About the Project
**Jenu-Gumpu** (ಜೇನು ಗುಂಪು) means "Honey Hunters Collective" in Kannada. This Android app empowers tribal and rural honey hunters to become their own brand by providing tools to track harvest, grade honey quality, monitor market prices, and calculate profits — all in Kannada language.

## 🚨 Problem Statement
Tribal and rural honey hunters sell raw honey to middlemen at very low prices (₹150/kg). But the same honey sells for ₹800 to ₹1200/kg in city markets. They lack knowledge of value addition, quality grading, and retail market prices. This app bridges that gap and empowers honey hunters to earn what they truly deserve.

## 💡 Solution
A mobile app that acts as a complete toolkit for honey hunters to record and track every honey harvest, grade honey quality using color and moisture checks, monitor wholesale vs retail market prices, track batches from forest to customer, and calculate profit after filtering costs.

## 📱 Features
| Feature | Description |
|---------|-------------|
| 📝 Harvest Log | Record date, location, quantity and floral source of honey |
| 🎨 Grading Tool | Visual guide to check honey quality — Grade A, B, or C |
| 💰 Price Monitor | Compare wholesale vs retail prices with editable market rates |
| 📦 Batch Tracker | Unique Batch ID for every honey jar for full traceability |
| 🧮 Profit Calculator | Calculate earnings after filtering costs vs middleman price |
| 📊 Live Dashboard | Real-time total collective stock and top floral source |

## 🛠️ Tech Stack
| Tool | Purpose |
|------|---------|
| Kotlin | Primary programming language |
| Android Studio | Development environment |
| Room Database | Local data storage |
| RecyclerView | Displaying list of entries |
| CardView | Beautiful UI cards |
| Flow & Coroutines | Live data sync with UI |
| SharedPreferences | Saving editable market prices |
| XML | Screen layout design |

## 🏆 Honey Grades
| Grade | Color | Moisture | Price Range |
|-------|-------|----------|-------------|
| 🥇 Grade A | Light Golden Yellow | Below 17% | ₹800 - ₹1200/kg |
| 🥈 Grade B | Amber / Dark Yellow | 17% - 20% | ₹500 - ₹800/kg |
| 🥉 Grade C | Dark Brown | Above 20% | ₹200 - ₹500/kg |

## 🎯 Project Requirements Met
- ✅ Categorize honey by Floral Source
- ✅ Collective Stock viewable as sum of all individual entries
- ✅ UI available in Kannada language
- ✅ Icons used for grading
- ✅ CardViews for different honey grades
- ✅ Room DB for storing harvest history
- ✅ Profit Calculator with filtering costs
- ✅ Batch ID for traceability

## 🚧 Challenges Faced
1. Designing a multi-field Harvest Log form with proper input validation.
2. Storing and syncing harvest entries live with UI using Room Database.
3. Generating unique Batch IDs automatically for every honey entry.
4. Building an interactive Grading Tool with dynamic grade calculation.
5. Designing consistent Kannada UI with CardViews across all screens.

## ✅ Solutions
1. Used EditText and RadioGroup with validation to collect and save harvest data.
2. Used Room Database with Flow to store and sync entries with UI automatically.
3. Generated unique Batch IDs using system timestamp for every harvest entry.
4. Built Grading Tool using Spinners and conditional logic to display grade dynamically.
5. Designed consistent UI using CardViews and Kannada strings across all screens.

## 🌿 Impact Goals
- 🌱 **Tribal Empowerment** — Improving livelihoods of forest-dwelling communities
- 🍯 **Organic Growth** — Promoting Forest-to-Table chemical-free honey products
- 🐝 **Sustainable Harvest** — Guidelines to harvest honey without killing bee colonies

## ⚙️ How to Run
```bash
git clone https://github.com/yourusername/JenuGumpu.git
```
1. Open in **Android Studio**
2. Let Gradle sync complete
3. Run on emulator or Android device (API 24+)

## 📁 Project Structure
```
com.intern.jenugumpu
├── MainActivity.kt
├── HarvestLogActivity.kt
├── GradingToolActivity.kt
├── PriceMonitorActivity.kt
├── BatchTrackerActivity.kt
├── ProfitCalculatorActivity.kt
├── HarvestEntry.kt
├── HarvestDao.kt
└── HarvestDatabase.kt
```

## 👨‍💻 Developer
- **Name:** Your Name
- **Internship:** Your Company Name
- **Project ID:** 37 — Android App Development using GenAI
- **Category:** Agriculture / Self-Employment

## 📄 License
This project was built as part of an internship program. All rights reserved © 2026

> *"Jenu-Gumpu is not just an app — it is a tool that empowers honey hunters to take control of their livelihood and earn what they truly deserve."* 🍯
