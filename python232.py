#!/usr/bin/python2

# control Dynamixel motor with ID=3
import serial, string

# for python3
# def write_port(*data):  # write_port(6, 10, 46, 46)
#     dataB = []
#     for i in range(len(data)):
#         dataB.insert(i,int(data[i],16))
#     ser.write( "".join(chr(i) for i in dataB) )

def write_port(data):
    dataB = []
    data = data.split(' ')
    for i in range(len(data)):
        dataB.insert(i,int(data[i],16))
    ser.write( "".join(chr(i) for i in dataB) )

ser = serial.Serial('/dev/ttyUSB0', 115200, timeout=1)
# write_port("ff ff 3 7 3 1e e8 3 c8 0 21")
write_port("ff ff 3 7 3 1e 1 0 c8 0 b")
