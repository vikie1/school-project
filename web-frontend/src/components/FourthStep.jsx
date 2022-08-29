export const FourthStep = ({dataFromForms}) => {
    return (
        <>
        Hello step 4!
        <button onClick={() => dataFromForms("hi", 5)}>Click me</button>
        </>
    );
}