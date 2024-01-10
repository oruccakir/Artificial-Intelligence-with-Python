from transformers import GPT2LMHeadModel, GPT2Tokenizer

def chat():
    tokenizer = GPT2Tokenizer.from_pretrained("gpt2")
    model = GPT2LMHeadModel.from_pretrained("gpt2")

    # Chatbot function
    while True:
        # User input
        input_text = input("User: ")
        if input_text.lower() in ['quit', 'exit', 'bye']:
            break

        # Encode the input text and add end of string token
        input_ids = tokenizer.encode(input_text + tokenizer.eos_token, return_tensors='pt')

        # Generate a response
        output = model.generate(input_ids, max_length=1000, num_return_sequences=1, no_repeat_ngram_size=2, early_stopping=True)
        response = tokenizer.decode(output[0], skip_special_tokens=True)

        print("Bot:", response)

if __name__ == "__main__":
    chat()
