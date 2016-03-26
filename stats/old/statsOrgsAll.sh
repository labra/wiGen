for orgs in 1 5 10 50 100 500 1000
do
 ./wigen -c 1 -g $orgs --dataSets 1 -o 1 --shex --shacl --time --no-single >> obsOrgsAll 
done