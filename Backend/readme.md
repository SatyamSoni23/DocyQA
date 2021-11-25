# Backend

The backend uses a flask server for Question-Answering System for getting requests then calling methods a/c to user requirements and posting answerds. It works on both short as well as long pdf files. It can automatically find answers to matching questions directly from pdf. The deep learning language model converts the questions and documents to semantic vectors to find the matching answer.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install following Libraries.

### For methods Naive and Word Embedding Technique
```bash
!pip install pdfplumber
!pip install nltk
!pip install -U gensim
!pip install flask-ngrok
!pip install werkzeug
!pip install numpy
```

### For Simple Transformer
```bash
!pip install simpletransformers
```

### For Bert
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
