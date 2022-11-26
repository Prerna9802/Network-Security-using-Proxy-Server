import time
import random 


def conn_string(conn, data, addr):
    first_line = data.decode('latin-1').split("\n")[0]
    print(first_line)
    url = first_line.split(" ")[1]
    
    http_pos = url.find("://")
    if http_pos == -1:
        temp = url
    else:
        temp = url[(http_pos + 3):]
        
    port_pos = temp.find(":")
    webserver_pos = temp.find("/")
    if webserver_pos == -1:
        webserver_pos = len(temp)
    webserver = ""
    port = -1
    if port_pos == -1 or webserver_pos < port_pos:
        port = 80
        webserver = temp[:webserver_pos]
    else:
        port = int(temp[(port_pos + 1):][:webserver_pos - port_pos -1])
        webserver = temp[:port_pos]
a=11236
#a = random.randint(10000, 12999);
 
print ('UserId:-')
userId= input ()
if userId == 'pk0669' or userId == 'ps5734':
    print("Searching in DataBase!..Please Wait...")
    time.sleep(1);
    print ('Hello User: ' + userId + '\nPassword: ')
    password= input ()
    if userId == 'pk0669' and password == 'piyush':
        print("Searching in DataBase!..Please Wait...")
        time.sleep(2);
        print('ACCESS GRANTED')
        time.sleep(3);
        print ("Access Listening PortId:",a)
        print("--------------------------------")
    elif userId== 'ps5734' and password == 'prerna':
        print ('ACCESS GRANTED')
        print("Searching in DataBase!..Please Wait...")
        time.sleep(3);
        print ("Access Listening PortId:",a)
        print("--------------------------------")

    else:
        time.sleep(1);
        print('Incorrect Password!') 
        print ('***ACCESS DENIED***')
    time.sleep(1);
    

else:
    print("Searching in DataBase!..Please Wait...")
    time.sleep(2);
    print ('No UserId Found!')
    print ('***ACCESS DENIED***')


    
