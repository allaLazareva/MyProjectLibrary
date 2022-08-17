package boot.convert.interf;

public interface IConverter<T,C>{
    T convert(C c);
}
