from transformers import GPT2LMHeadModel, GPT2Tokenizer
import torch

# Load pre-trained model tokenizer (vocabulary)
tokenizer = GPT2Tokenizer.from_pretrained('gpt3')

# Load pre-trained model (weights)
model = GPT2LMHeadModel.from_pretrained('gpt3')

# Set the model to evaluation mode to deactivate the DropOut modules
model.eval()

# Function to ask a question to the chatbot
def ask_question(question, length=50):
    inputs = tokenizer.encode(question, return_tensors='pt')
    outputs = model.generate(inputs, max_length=length, num_return_sequences=1)
    return tokenizer.decode(outputs[0], skip_special_tokens=True)

# Example conversation
prompt = ''
while prompt != "quit":
    prompt = input("You:... ")
    answer = ask_question(prompt)
    print(answer)


