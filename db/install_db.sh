timestamp=`date +%Y%m%d%H%M%S`

mkdir -p logs
mkdir -p /c/Users/l38117419/robots/db/robots_data
mkdir -p /c/Users/l38117419/robots/db/robots_index

export PGPASSWORD=root
export HOSTNAME=localhost
echo "Database creation - start"
psql -h $HOSTNAME -U postgres -f create-db.sql -o ./logs/${timestamp}_create-db.log &>> ./logs/${timestamp}_install-db.log
echo "Database creation - end"