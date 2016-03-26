for datasets in 1 5 10 50 100 500 1000
do
 ./wigen -c 1 -g 1 --dataSets $datasets -o 1 --shex --shacl --time --no-single >> obsDatasets 
done