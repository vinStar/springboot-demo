#!/usr/bin/env python
# _*_ coding:utf-8 _*_

import smtplib
from email.mime.text import MIMEText
import sys

# configure your own parameters here
#下面邮件地址的smtp地址
mail_host = 'smtp.exmail.qq.com'
#用来发邮件的邮箱,在发件人抬头显示(不然你的邮件会被当成是垃圾邮件)
mail_user = 'jianqian@eq102.com'
list_mail = ['liutong@eq102.com']
# 客户端授权码
mail_auth = 'iaE6p7ppMxRmh237'
# 发送方显示的名称
send_name = mail_user
# 接收方显示的名称
recv_name = mail_user

def excute(title, content):
    msg = MIMEText(content, 'plain', 'utf-8')
    msg['From'] = send_name
    msg['To'] = recv_name
    msg['Subject'] = title
    server = smtplib.SMTP_SSL(mail_host, 465)
    server.login(mail_user,mail_auth)
    server.sendmail(mail_user,list_mail,msg.as_string())
    server.quit()

if __name__ == '__main__':
    excute(sys.argv[1], sys.argv[2])
#                 $1           $2
