# COSC345 AI Generated Poetry Group Project
[![Documentation](https://github.com/jordankettles/345-group-project/actions/workflows/documentation.yml/badge.svg)](https://github.com/jordankettles/345-group-project/actions/workflows/documentation.yml)
[![Documentation](https://github.com/jordankettles/345-group-project/actions/workflows/android.yml/badge.svg)](https://github.com/jordankettles/345-group-project/actions/workflows/android.yml)
[![codecov](https://codecov.io/gh/jordankettles/345-group-project/branch/main/graph/badge.svg?token=O6ADELJECI)](https://codecov.io/gh/jordankettles/345-group-project)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/54b9bb2d15b14967853a825d9a0b5d87)](https://www.codacy.com/gh/jordankettles/345-group-project/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jordankettles/345-group-project&amp;utm_campaign=Badge_Grade)
## [Project Wiki](https://github.com/jordankettles/345-group-project/wiki/)
We are building an app called Deep Poet that uses a custom-trained AI to generate poetry based on short prompts. After the AI has generated a poem, users can select a new prompt or view a list of recent poems. Deep Poet will let our users find inspiration when stuck in a creative block, impress their friends and lovers with their new-found literary skill, or simply waste their fleeting time on this planet reading GPT-2’s attempts to pass a poetic version of the Turing test.

## Beta
Like the Alpha version of our app, our Beta version uses Hugging Face's GPT-2 model under Apache 2.0 License. Unlike the Alpha version (which did not alter GPT-2), the Beta version’s GPT-2 model has been fine-tuned on a dataset of poems scraped from the Poetry Foundation’s website. Our aim in doing so was for GPT-2 to generate more noticeably poetic text. Although we’re largely happy with the results, we should note that biases from the original training data do occasionally bleed into the poems; we apologize in advance for any language that you wouldn’t use in front of your grandmother. 

### Building the Beta
To build the Beta, Sync the Project with Gradle Files then Build the Project in Android Studio.

Android Studio needs to sync to download the GPT-2 model, which cannot be uploaded to GitHub
because of their 100MB file size limit. The model is 236MB.

### New Features
•	We added a button to let users share poems to other apps
•	We finetuned GPT-2 on a dataset of poems from the Poetry Foundation website
•	We updated the UI
•	We added animation
•	We named the app (Deep Poet)
• We added analysis reports (Codacy and Codecov)

### Issues
•	We have a select head button that is currently just a placeholder. 
•	Our tests were run locally and uploaded manually because GitHub actions does not work with our app (we’ve opened up an issue with GitHub, and they said they’ll get back to us).

### New Team
Out of the fire that was Andrew’s chaotic neutral upheaval at the start of Semester 2 flies the phoenix of a new team: Jordan Kettles, Masaaki Fukushima, Max Freeman, and Nat Moore. 
