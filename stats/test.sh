values="1 2" 
# values="1 5 10 50 100 500 1000" 
wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"
#wiGenRunner=./wiGen

for value in $values
do
outName="outCountries"
params="-c $value -d 1 --slices 1 -o 1 -p 1 -i 1 -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outDataSets"
params="-c 1 -d $value --slices 1 -o 1 -p 1 -i 1 -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outSlices"
params="-c 1 -d 1 --slices $value -o 1 -p 1 -i 1 -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outObs"
params="-c 1 -d 1 --slices 1 -o $value -p 1 -i 1 -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outComps"
params="-c 1 -d 1 --slices 1 -o 1 -p $value -i 1 -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outIndicators"
params="-c 1 -d 1 --slices 1 -o 1 -p 1 -i $value -g 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outOrgs"
params="-c 1 -d 1 --slices 1 -o 1 -p 1 -i 1 -g $value --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

done


