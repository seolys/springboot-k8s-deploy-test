# build : docker image build -t outsiderys/mysql:버전 .
# push : docker image push outsiderys/mysql:버전
FROM mysql:5.7

COPY etc/mysql/mysql.conf.d/mysqld.cnf /etc/mysql/conf.d/
COPY etc/mysql/conf.d/mysql.cnf /etc/mysql/conf.d/

CMD ["mysqld"]