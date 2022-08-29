export const ThirdStep = ({dataFromForms}) => {
    return (
        <>
        Hello step 3!
        <button onClick={() => dataFromForms("hi", 4)}>Click me</button>
        </>
    );
}