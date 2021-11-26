# DocyQA
Question Answering System for Android Devices. 4 approaches implemented in backend for QA System i.e., Naive Approach, Word Embedding Technique (Word2Vec, Glove), Simple transformer and Bert. For frontend, an Android app is used.

## Android Dependencies
```bash
implementation 'com.squareup.okhttp3:okhttp:4.5.0'
implementation 'com.google.firebase:firebase-storage:20.0.0'
implementation 'com.google.firebase:firebase-config:21.0.1'
```

## Backend

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install the following Libraries.

### For Naive and Word Embedding Technique
```bash
!pip install pdfplumber
!pip install nltk
!pip install -U gensim
!pip install flask-ngrok
!pip install werkzeug
!pip install numpy
```

### Simple Transformer
```bash
!pip install simpletransformers
```

### Bert
```bash
!pip install transformers==3.1.0
```

### Deployment Side
```bash
!pip install Flask
!pip install pyrebase
!pip install flask-ngrok
!pip install werkzeug
```

## Approches:
- Simple Split and Cosine Similarity (Naive Approach)
- Word2Vec Word Embedding Technique
- Glove Word Embedding Technique
- Simple Transformer technique
- Question Answering System with Fine-Tuned BERT Technique

## Pretrained Model and Dataset Used
- word2vec
- glove
- bert-large-uncased-whole-word-masking-finetuned-squad
- bert-squad_1.1


## Simple Transformer Training Dataset
- Squad_1.1

## Firebase Usage
Don't need to update ngrok public URL in the frontend code just update URL in remote config in firebase and fetch the remote config URL from the android side.

## Setup

- Setup Storage in Firebase and create "docs" folder.
- Make a new Parameter "URL" in Remote Config in Firebase that store the public URL of ngrok in future steps.
- Open Backend/DocyQA.ipynb file in Google Colab and execute all cells except flask server.
- Create a new folder "docs" in Google Colab folder in Google Drive
- Run Flask Server.
- Copy the ngrok public URL and paste it to Firebase Remote Config Parameter URL and Publish it.
- Now Setup Android Studio and Firebase to create the APK file.
