import re
from datetime import datetime

def get_file_content_as_list(filename="log_q1.txt"):
    content = []
    with open(filename) as file:
        # read the content of the file
        for line in file:
            line = line.strip()
            line = re.findall(r'[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+.*-.*-.*\[.*\].*',line)
            if len(line) >= 1:
                content.append(line[0])

    return content

def write_results_to_file(entries,results,filename="output_q1.txt"):
    # writes results to output file
    with open(filename,'w') as file:
        for i in range(0,len(entries)):
            line = f"{entries[i].ip_address} "+f"{results[i]}\n"
            file.write(line)

def print_the_contents(contents):
    # print the contents line by line
    for content in contents:
        print(content)

def print_the_entries(entries):
    # print the contents line by line
    for entry in entries:
        print(entry)

def create_entries(contents):
    # create entries according to content of the file
    entries = []
    for content in contents:
        str1 = re.findall(r'[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+',content)[0]
        str2 = re.findall(r'[0-9]{2}/[a-zA-Z]{3}/[0-9]{4}',content)[0]
        str3 = re.findall(r'\b[0-9]{2}:[0-9]{2}:[0-9]{2}',content)[0]
        str4 = re.findall(r'\+[0-9]{4}',content)[0]
        new_entry = Entry(str1,str2,str3,str4)
        entries.append(new_entry)
    
    return entries

def is_leap_year_control(year):
    # checks whether year is leap or not
    return (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)
    
def convert_from_year_to_days(year):
    # converts year to days
    return 366 if is_leap_year_control(year) else 365

def convert_from_months_to_days(month,year):
    # convert months to days
    month_index = 1

    if(month.__eq__("Jan")):
        month_index = 1
    elif(month.__eq__("Feb")):
        month_index = 2
    elif(month.__eq__("Mar")):
        month_index = 3
    elif(month.__eq__("Apr")):
        month_index = 4
    elif(month.__eq__("May")):
        month_index = 5
    elif(month.__eq__("Jun")):
        month_index = 6
    elif(month.__eq__("Jul")):
        month_index = 7
    elif(month.__eq__("Aug")):
        month_index = 8
    elif(month.__eq__("Sep")):
        month_index = 9
    elif(month.__eq__("Oct")):
        month_index = 10
    elif(month.__eq__("Nov")):
        month_index = 11
    elif(month.__eq__("Dec")):
        month_index = 12
        
    months = {
        1: 31,
        2: 29 if is_leap_year_control(year) else 28,
        3: 31,
        4: 30,
        5: 31,
        6: 30,
        7: 31,
        8: 31,
        9: 30,
        10: 31,
        11: 30,
        12: 31,
    }

    return months.get(month_index, 0)

def get_output_dictionary(entries):
    """
    Gets entries as parameter and return a list of result timestamp values
    """
    result = []

    length = len(entries)
    difference = 0

    for i in range(0,length,1):
        difference = 0
        for k in range(i,-1,-1):
            if(entries[k].ip_address.__eq__(entries[i].ip_address)):
                if(entries[i].total_seconds - entries[k].total_seconds >= difference):
                    difference = entries[i].total_seconds - entries[k].total_seconds
        
        result.append(difference)
        
    
    return result


class Entry:

    def __init__(self,ip_address,date,time,time_des):
        self.ip_address = ip_address
        self.date = date
        self.time = time
        date_split = re.split(r'/',self.date)
        time_split = re.split(r':',self.time)
        self.day = int(date_split[0])
        self.month  = date_split[1]
        self.year = int(date_split[2])
        self.hour = int(time_split[0])
        self.minute = int(time_split[1])
        self.second = int(time_split[2])
        self.time_des = time_des

        month_index = 1

        month = self.month

        if(month.__eq__("Jan")):
            month_index = 1
        elif(month.__eq__("Feb")):
            month_index = 2
        elif(month.__eq__("Mar")):
            month_index = 3
        elif(month.__eq__("Apr")):
            month_index = 4
        elif(month.__eq__("May")):
            month_index = 5
        elif(month.__eq__("Jun")):
            month_index = 6
        elif(month.__eq__("Jul")):
            month_index = 7
        elif(month.__eq__("Aug")):
            month_index = 8
        elif(month.__eq__("Sep")):
            month_index = 9
        elif(month.__eq__("Oct")):
            month_index = 10
        elif(month.__eq__("Nov")):
            month_index = 11
        elif(month.__eq__("Dec")):
            month_index = 12


        date = ""+str(self.year)+"-"+str(month_index)+"-"+str(self.day)+" "+str(self.hour)+":"+str(self.minute)+":"+str(self.second)
        parse_date = datetime.strptime(date,"%Y-%m-%d %H:%M:%S")
        self.total_seconds = int(parse_date.timestamp())

        if self.time_des.__eq__("+0100"):
            self.total_seconds = self.total_seconds + 60*60
        elif self.time.__eq__("+0200"):
            self.total_seconds = self.total_seconds + 2*60*60



        #self.total_seconds = self.year * convert_from_year_to_days(self.year-1) * 24 * 3600 + convert_from_months_to_days(self.month,self.year) * 24 * 3600 + self.day * 24 * 3600 + self.hour * 3600 + self.minute * 60 + self.second
    
        
    def __str__(self):
        return f"Ip address : {self.ip_address}"+f" Day : {self.day} "+f"Month : {self.month} "+f"Year : {self.year} "+f"Hour : {self.hour} "+f"Minute : {self.minute} "+ f"Second : {self.second}"
    
    

contents = get_file_content_as_list(filename="log_q1.txt")

entries = create_entries(contents)

results =get_output_dictionary(entries)

write_results_to_file(entries,results)