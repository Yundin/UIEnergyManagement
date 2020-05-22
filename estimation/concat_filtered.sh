rm result &>/dev/null
for dir in $@; do
    if [ ! -d "$dir" ]; then
        rm result &>/dev/null
        echo "Pass directories only"
        exit 1
    fi
    for f in $dir*; do
        echo $f >> result
        cat $f | head -n1 | cut -d' ' -f 13 >> result
        cat $f | sed -nE 's/.*(u=.*) (s=.*)/\1 \2/p' >> result
        echo >> result
    done
done
