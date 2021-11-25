# Backend

The backend uses a flask server for Question-Answering System for getting requests then calling methods a/c to user requirements and posting answerds. It works on both short as well as long pdf files. It can automatically find answers to matching questions directly from pdf. The deep learning language model converts the questions and documents to semantic vectors to find the matching answer.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install following Libraries.

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
Don't need to update ngrok public url in frontend code just update url in remote config in firebase and fetch remote config url from android side.
