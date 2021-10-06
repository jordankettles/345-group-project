# COSC345 AI Generated Poetry Group Project
[![Documentation](https://github.com/jordankettles/345-group-project/actions/workflows/documentation.yml/badge.svg)](https://github.com/jordankettles/345-group-project/actions/workflows/documentation.yml)
[![Documentation](https://github.com/jordankettles/345-group-project/actions/workflows/android.yml/badge.svg)](https://github.com/jordankettles/345-group-project/actions/workflows/android.yml)
[![codecov](https://codecov.io/gh/jordankettles/345-group-project/branch/main/graph/badge.svg?token=O6ADELJECI)](https://codecov.io/gh/jordankettles/345-group-project)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/54b9bb2d15b14967853a825d9a0b5d87)](https://www.codacy.com/gh/jordankettles/345-group-project/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jordankettles/345-group-project&amp;utm_campaign=Badge_Grade)
## [Project Wiki](https://github.com/jordankettles/345-group-project/wiki/)
We have built an app called Deep Poet that uses a custom-trained deep learning AI to generate poetry based on short prompts. After the AI has generated a poem, users can continue generating the poem, share the poem, or write a new prompt. Each generated poem is automatically saved and can be viewed at any time through the app. Users can also change the hand-drawn animated poet that appears at the bottom of the screen. 
Deep Poet will let our users find inspiration when stuck in a creative block, impress their friends and lovers with their new-found literary skill, or simply waste their fleeting time on this planet reading GPT-2’s attempts to pass a poetic version of the Turing test.


## Final Release
The final version of our app uses Hugging Face’s GPT-2 model under Apache 2.0 License. The model has been fine-tuned on a dataset of poems scraped from the Poetry Foundation website. You can find the notebook on how we fine-tuned the model’s output [here.](https://github.com/jordankettles/345-group-project/tree/main/gpt2-notebook)

### Building the App
To build the app, Sync the Project with Gradle Files then Build the Project in Android Studio.

Android Studio needs to sync to download the GPT-2 model, which cannot be uploaded to GitHub because of their 100MB file size limit. The model is 236MB.    

### New Features
• We improved our code coverage and code quality, as measured by Codecov and Codacy
• We added more hand-drawn poet characters for users to select, and gave each character a name
• We sanitized the model’s output by writing code that censors forbidden words from generated poems.
• We trained the GPT-2 model for more epochs and further lowered its loss 
• We added an option to generate more lines to extend an existing poem
• We added more tests and altered existing tests 
• We added code to format the output of generated poems (for example, letters after full stops are now automatically capitalized) 
• We added functionality to save the generated poems to storage so they persist after app close.
• We added an option to delete poems from storage
• We updated the help message to reflect changes to the app


### Issues
•	Our tests were run locally and uploaded manually because GitHub actions does not work with our app (we’ve opened up an issue with GitHub, and they said they’ll get back to us).

### What We Learned From Our Beta Version
• We learned that the GitHub actions runner for the Android Emulator has too small a heap size to support running our app
• We learned how to use activities properly
• We learned how to write better tests


### Semester Two Team
Jordan Kettles, Masaaki Fukushima, Max Freeman, and Nat Moore. 
