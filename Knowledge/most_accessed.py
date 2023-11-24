import re

def get_file_content_as_list(filename="log_q2.txt"):
    content = []
    with open(filename) as file:
        # read the content of the file
        for line in file:
            line = line.strip()
            line = re.findall(r'[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+ - - \[.*\].*\"GET.*',line)
            
            if(len(line) != 0):
                line = re.findall(r'.*HTTP',line[0])
                line = re.split(r'/',line[0])
                line = re.split(r' ',line[len(line)-1])
                if(len(line[0]) != 0):
                    content.append("/"+line[0])
            

    return content

def convert_content_to_sorted_entries(contents):
    """
    takes contents as paremeter and return map dictionary of filename as a key and its frequency as a value
    """

    dict = {}

    entries = []

    for content in contents:
        if content in dict:
            dict[content] = dict[content] + 1
        else:
            dict[content] = 1
    
    for key in dict:
        entries.append(Entry(key,dict[key]))

    
    return  sorted(entries)


def write_the_results_to_file(entries,filename="output_q2.txt"):
    """
    Takes sorted list and writes to the given file
    """
    with open(filename,'w') as file:
        for entry in entries:
            file.write(f"{entry.filename} {entry.frequency}\n")



def print_the_contents(contents):
    # print the contents line by line
    for content in contents:
        print(content)

def print_the_entries(entries):
    # print the contents line by line
    for entry in entries:
        print(entry)
    

class Entry:

    def __init__(self,filename,frequency):
        self.filename = filename
        self.frequency = frequency
    
    def __str__(self):
        return f"{self.filename} {self.frequency}"
    
    def __lt__(self,other):
        if(self.frequency > other.frequency):
            return True
        elif(self.frequency < other.frequency):
            return False
        else:
            if(self.filename > other.filename):
                return True
            else:
                return False
            
            
    
    


contents = get_file_content_as_list()

entries = convert_content_to_sorted_entries(contents)

write_the_results_to_file(entries)